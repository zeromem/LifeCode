package org.zeromem.lifecode.paxos;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.zeromem.lifecode.paxos.message.Message;
import scala.concurrent.duration.Duration;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.zeromem.lifecode.paxos.Constants.AKKA_SYS_PAXOS_PREFIX;
import static org.zeromem.lifecode.paxos.Constants.ID_FRACTION_RESTRICT;
import static org.zeromem.lifecode.paxos.Constants.NUM_MAJORITY;


/**
 * Created by zeromem on 2017/9/26.
 */
public class Proposer extends AbstractActor {
	// log of this's actor
	private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	// props to create this actor
	static public Props props(final Integer id, final Config config) {
		return Props.create(Proposer.class, () -> new Proposer(id, config));
	}


	@Override
	public void preStart() throws Exception {
		super.preStart();
	}

	public final Integer id;
	private final Double fractionId;

	/**
	 * 每个key当前使用的提案号
	 */
	private HashMap<String, Double> keyToCurUniq;

	/**
	 * 由client指定的(key, value)
	 */
	private HashMap<String, Value> presetValue;

	/**
	 * prepare阶段回复提案N OK的所有acceptor
	 */
	private WeakHashMap<Double, Set<ActorRef>> prepareOKAcceptors;

	/**
	 * prepare阶段回复提案N的Reject数量(无需保存各acceptor)
	 * todo: 也应该记录所有reject的acceptor，后续Accept阶段时，可以向{all_acceptors - reject_acceptors}发送accept请求
	 */
	private WeakHashMap<Double, Integer> prepareRejectCounter;

	/**
	 * prepare阶段回复提案N的其他proposer的最大提案号
	 */
	private HashMap<Double, Double> highestNumber;

	/**
	 * prepare阶段回复提案N的其他proposer的最大提案号对应的值
	 */
	private HashMap<Double, Value> receivedValue;


	/**
	 * accept阶段回复提案N为OK的计数
	 */
	private HashMap<Double, Integer> acceptOKCounter;

	/**
	 * accept阶段回复提案N为Reject的计数
	 */
	private HashMap<Double, Integer> acceptRejectCounter;


	private Set<ActorSelection> acceptors;

	/**
	 * @param id assigned proposer id.
	 */
	public Proposer(Integer id, Config config) {
		this.id = id;
		this.fractionId = id / ID_FRACTION_RESTRICT;

		// todo 考虑哪些map用WeakHashMap
		keyToCurUniq = new HashMap<>();
		presetValue = new HashMap<>();
		prepareOKAcceptors = new WeakHashMap<>();
		prepareRejectCounter = new WeakHashMap<>();
		highestNumber = new HashMap<>();
		receivedValue = new HashMap<>();

		init(config);
	}

	private void init(Config config) {
		List<String> acceptors = config.getStringList("acceptors");
		final Integer port = config.getInt("acceptor.port");

		this.acceptors = acceptors.stream().map(host -> {
			String sb = AKKA_SYS_PAXOS_PREFIX +
					host +
					":" +
					port +
					"/user/acceptor-*";
			return getContext().actorSelection(sb);
		}).collect(Collectors.toSet());
	}

	// main logic of this actor
	@Override public Receive createReceive() {
		ReceiveBuilder builder = ReceiveBuilder.create();

		//////////// client 发起新请求. ///////////
		builder.match(Message.ClientRequest.class, clientRequest -> {
			// 保存预设值
			String key = clientRequest.key;
			presetValue.put(key, clientRequest.value);

			// 使用新的提案号
			Double uniq = keyToCurUniq.getOrDefault(key, fractionId) + 1;
			keyToCurUniq.put(key, uniq);

			// 向所有acceptor发送prepare todo 从配置文件加载acceptors
			ActorSelection acceptors = getContext().actorSelection("/acceptor-*");
			acceptors.tell(new Message.Prepare(clientRequest.key, uniq), getSelf());

			// 设置超时，超时后prepare阶段自动失败
			getContext().getSystem().scheduler().scheduleOnce(
					Duration.create(10, TimeUnit.SECONDS),
					getSelf(),
					new Message.PrepareTimeout(key, uniq),
					getContext().getSystem().dispatcher(),
					getSelf()
			);
		});

		////////// prepare阶段 //////////////////
		// acceptor回复的PrepareOK消息
		builder.match(Message.PrepareOK.class, ok -> {
			Double uniq = ok.uniq;
			String key = ok.key;
			// 只处理自己最新提案号的相关请求
			if (!checkValid(key, uniq)) {
				return;
			}

			// 将回复ok的acceptor保存（后续需要向他们发送Accept请求）
			prepareOKAcceptors.putIfAbsent(uniq, new HashSet<>());
			prepareOKAcceptors.get(uniq).add(getSender());

			// 如果回复中包含其他已有提案编号 且 编号更大，则将其作为自己后续的提案值
			Double acceptedUniq = ok.AcceptedUniq;
			if (acceptedUniq != null && acceptedUniq > highestNumber.getOrDefault(uniq, 0d)) {
				highestNumber.put(uniq, acceptedUniq);
				receivedValue.put(uniq, ok.AcceptedValue);
			}

			// 如果当前已收到大多数acceptor的ok回复，则发起第二阶段的Accept请求
			if (prepareOKAcceptors.get(uniq).size() >= NUM_MAJORITY) {
				Value value = receivedValue.getOrDefault(uniq, presetValue.get(key));
				refMulticast(prepareOKAcceptors.get(uniq), new Message.Accept(key, uniq, value));
			}
		});

		// acceptor回复PrepareReject消息
		builder.match(Message.PrepareReject.class, reject -> {
			Double uniq = reject.uniq;
			// 只处理自己最新提案号的相关请求
			if (!checkValid(reject.key, uniq)) {
				return;
			}

			// reject 计数加1
			int numReject = prepareRejectCounter.getOrDefault(uniq, 0) + 1;
			prepareRejectCounter.put(uniq, numReject);
			if (numReject >= NUM_MAJORITY) {
				fail(reject.key, uniq);
			}
		});

		// prepare阶段超时标记
		builder.match(Message.PrepareTimeout.class, timeout -> {
			if (checkValid(timeout.key, timeout.uniq)) {
				keyToCurUniq.put(timeout.key, timeout.uniq + 1);
			}

		});
		///////////////////////////////////////////////

		///////////// accept阶段 /////////////////////
		builder.match(Message.AcceptOK.class, ok -> {
			String key = ok.key;
			Double uniq = ok.uniq;
			if (!checkValid(key, uniq)) {
				return;
			}
			int numOK = acceptOKCounter.getOrDefault(uniq, 0) + 1;
			acceptOKCounter.put(uniq, numOK);
			if (numOK >= NUM_MAJORITY) {
				// 提案成功!
				log.info("proposal success!");
			}
		});

		builder.match(Message.AcceptReject.class, reject -> {
			String key = reject.key;
			Double uniq = reject.uniq;
			if (!checkValid(key, uniq)) {
				return;
			}
			int numReject = acceptRejectCounter.getOrDefault(uniq, 0) + 1;
			acceptRejectCounter.put(uniq, numReject);
			if (numReject >= NUM_MAJORITY) {
				// 提案失败!
				log.info("proposal failed!");
			}
		});

		builder.match(Message.AcceptTimeout)

		builder.matchAny(o -> log.info("received unknown message.")).build();
		return builder.build();
	}

	/**
	 * 检查请求是否还有效
	 * @param key
	 * @param uniq
	 * @return
	 */
	private Boolean checkValid(String key, Double uniq) {
		return uniq.equals(keyToCurUniq.get(key));
	}

	/**
	 * 想要设置key值的提案uniq失败,将key对应的提案号提高
	 * @param key
	 * @param uniq
	 */
	private void fail(String key, Double uniq) {
		Double curUniq = keyToCurUniq.get(key);
		if (curUniq - uniq < 1) {
			keyToCurUniq.put(key, curUniq + 1);
		}
	}

	/**
	 * 向所有targets发送message
	 * @param targets
	 * @param message
	 */
	private void selMulticast(Set<ActorSelection> targets, final Message message) {
		targets.forEach(target -> {
			target.tell(message, getSelf());
		});
	}

	private void refMulticast(Set<ActorRef> targets, final Message message) {
		targets.forEach(target -> {
			target.tell(message, getSelf());
		});
	}



	public static void main(String[] args) throws InterruptedException {
		Config config = ConfigFactory.load().getConfig("paxos");
		if (!config.getString("role").equals("proposer")) {
			throw new IllegalStateException(
					"only proposer can launch Proposer process! set [role] to proposer in application.conf");
		}
		Integer id = config.getInt("id");

		ActorSystem system = ActorSystem.create("paxos", config.getConfig("proposer"));
		ActorRef proposer = system.actorOf(Proposer.props(id, config), "proposer-" + id);


		Thread.sleep(5000);

		proposer.tell(new Message.ClientRequest("test", Value.of("hello world")), ActorRef.noSender());
	}

}
