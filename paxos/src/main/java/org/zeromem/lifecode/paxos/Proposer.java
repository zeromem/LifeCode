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

import static org.zeromem.lifecode.paxos.Constants.NUM_MAJORITY;


/**
 * Created by zeromem on 2017/9/26.
 */
public class Proposer extends AbstractActor {
	// log of this's actor
	private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	// props to create this actor
	static public Props props(final Integer id) {
		return Props.create(Proposer.class, () -> new Proposer(id));
	}


	@Override
	public void preStart() throws Exception {
		super.preStart();
	}

	public final Integer id;
	private Double curUniq;

	private HashMap<String, Value> presetValue;

	/**
	 * 回复提案N OK的所有acceptor
	 */
	private HashMap<Double, Set<ActorRef>> prepareOKAcceptors;

	/**
	 * 回复提案N的Reject数量(无需保存各acceptor)
	 */
	private HashMap<Double, Integer> prepareRejectCounter;

	/**
	 * 回复提案N的其他proposer的最大提案号
	 */
	private HashMap<Double, Double> highestNumber;

	/**
	 * 回复提案N的其他proposer的最大提案号对应的值
	 */
	private HashMap<Double, Value> receivedValue;


	private List<ActorSelection> acceptors;

	/**
	 * @param id assigned proposer id.
	 */
	public Proposer(Integer id) {
		this.id = id;
		curUniq = id / Constants.ID_FRACTION_RESTRICT;

		presetValue = new HashMap<>();
		prepareOKAcceptors = new HashMap<>();
		prepareRejectCounter = new HashMap<>();
		highestNumber = new HashMap<>();
		receivedValue = new HashMap<>();

	}

	// main logic of this actor
	@Override public Receive createReceive() {
		ReceiveBuilder builder = ReceiveBuilder.create();

		builder.matchAny(a -> log.info("Received an new message! {}", a));

		// client launch an new request.
		builder.match(Message.ClientRequest.class, clientRequest -> {
			presetValue.put(clientRequest.key, clientRequest.value);
			// 向所有acceptor发送prepare
			ActorSelection acceptors = getContext().actorSelection("/acceptor-*");
			acceptors.tell(new Message.Prepare(curUniq), getSelf());

			// 设置超时，超时后prepare阶段自动失败
			getContext().getSystem().scheduler().scheduleOnce(
					Duration.create(10, TimeUnit.SECONDS),
					getSelf(),
					new Message.PrepareResponseTimeout(),
					getContext().getSystem().dispatcher(),
					null
			);
		});

		// acceptor回复的PrepareOK消息
		builder.match(Message.PrepareOK.class, ok -> {
			Double uniq = ok.uniq;

			// 将回复ok的acceptor保存（后续需要向他们发送Accept请求）
			prepareOKAcceptors.putIfAbsent(uniq, new HashSet<>());
			prepareOKAcceptors.get(uniq).add(getSender());

			// 如果回复中包含其他已有提案编号 且 编号更大，则将其作为自己后续的提案值
			Double acceptedUniq = ok.AcceptedUniq;
			if (acceptedUniq != null && acceptedUniq > highestNumber.getOrDefault(uniq, 0d)) {
				highestNumber.put(uniq, acceptedUniq);
				receivedValue.put(uniq, ok.AcceptedValue);
			}

			// 如果当前已收到大多数acceptor的ok回复，则发起下阶段的Accept请求
			if (prepareOKAcceptors.get(uniq).size() >= NUM_MAJORITY) {
				String key = ok.key;
				Value value = receivedValue.getOrDefault(uniq, presetValue.get(key));
				for (ActorRef acceptor : prepareOKAcceptors.get(uniq)) {
					acceptor.tell(new Message.Accept(key, uniq, value), getSelf());
				}
			}
		});

		// todo.
		builder.match(Message.PrepareReject.class, reject -> {
			Double uniq = reject.uniq;

		});

		builder.matchAny(o -> log.info("received unknown message.")).build();
		return builder.build();
	}

	public void setAcceptors(List<ActorSelection> acceptors) {
		this.acceptors = acceptors;
	}

	public static void main(String[] args) throws InterruptedException {
		Config config = ConfigFactory.load().getConfig("paxos");
		if (!config.getString("role").equals("proposer")) {
			throw new IllegalStateException("only proposer can launch Proposer process! set [role] to proposer in application.conf");
		}

		ActorSystem system = ActorSystem.create("paxos");
		ActorRef proposer = system.actorOf(Proposer.props(1), "proposer-" + 1);

		System.out.println(proposer);

		Thread.sleep(5000);

		proposer.tell(new Message.ClientRequest("test", Value.of("hello world")), ActorRef.noSender());
	}

}
