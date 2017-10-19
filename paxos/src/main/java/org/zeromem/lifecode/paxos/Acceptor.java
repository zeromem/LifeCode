package org.zeromem.lifecode.paxos;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValueFactory;
import org.zeromem.lifecode.paxos.message.Message;

import java.util.HashMap;

import static org.zeromem.lifecode.paxos.Constants.LITERAL_ACCEPTOR;
import static org.zeromem.lifecode.paxos.Constants.LITERAL_PROPOSER;
import static org.zeromem.lifecode.paxos.Constants.LITERAL_ROLE;


/**
 * @author zeromem
 * @date 2017/9/26
 */
public class Acceptor extends AbstractActor {
	private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	static public Props props(final int id) {
		return Props.create(Acceptor.class, () -> new Acceptor(id));
	}

	public final Integer id;

	private final HashMap<String, Double> highestPrepareUniq;
	private final HashMap<String, Double> highestAcceptUniq;
	private final HashMap<String, Value> highestAcceptValue;

	public Acceptor(int id) {
		this.id = id;
		highestPrepareUniq = new HashMap<>();
		highestAcceptUniq = new HashMap<>();
		highestAcceptValue = new HashMap<>();
	}

	@Override
	public Receive createReceive() {
		ReceiveBuilder builder = ReceiveBuilder.create();
		builder.match(Message.Prepare.class, prepare -> {
			String key = prepare.key;
			Double uniq = prepare.uniq;
			Double highUniq = highestPrepareUniq.getOrDefault(key, 0d);
			if (uniq < highUniq) {
				sender().tell(new Message.PrepareReject(key, uniq), self());
			} else {
				highestPrepareUniq.put(key, uniq);
				// PrepareOK里面的acceptedUniq和acceptedValue可能为null.
				Double acceptedUniq = highestAcceptUniq.get(key);
				Value acceptedValue = highestAcceptValue.get(key);
				sender().tell(new Message.PrepareOK(key, uniq, acceptedUniq, acceptedValue), self());
			}
		});

		builder.match(Message.Accept.class, accept -> {
			String key = accept.key;
			Double uniq = accept.uniq;
			if (uniq < highestPrepareUniq.getOrDefault(key, 0d)) {
				sender().tell(new Message.AcceptReject(key, uniq), self());
			} else {
				highestPrepareUniq.put(key, uniq);
				Value value = accept.value;
				if (value != null) {
					highestAcceptUniq.put(key, uniq);
					highestAcceptValue.put(key, value);
				}
				sender().tell(new Message.AcceptOK(key, uniq, value), self());
			}
		});

		builder.matchAny(o -> log.warning("received unknown message!"));
		return builder.build();
	}

	public static void main(String[] args) {
		Config rawConf = ConfigFactory.load().getConfig("paxos");
		String role = rawConf.getString(LITERAL_ROLE);
		if (!LITERAL_PROPOSER.equals(role)) {
			throw new IllegalStateException(
					"only proposer can launch Proposer process! set [role] to proposer in application.conf");
		}
		Integer id = rawConf.getInt("id");
		Integer port = rawConf.getInt(role + ".port");
		Config config = rawConf.withValue("akka.remote.netty.tcp.port", ConfigValueFactory.fromAnyRef(port));

		ActorSystem system = ActorSystem.create("paxos", config);
		ActorRef acceptor = system.actorOf(Acceptor.props(id), "acceptor-" + id);
	}
}
