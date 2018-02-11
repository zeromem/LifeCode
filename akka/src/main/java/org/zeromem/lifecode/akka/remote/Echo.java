package org.zeromem.lifecode.akka.remote;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.typesafe.config.ConfigFactory;
import sun.text.resources.no.CollationData_no;

/**
 * @author zeromem
 * @date 2017/10/18
 */
public class Echo extends AbstractActor {
	private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	/** props to create this actor
	 *
	 */
	static public Props props() {
		return Props.create(Echo.class);
	}

    static public Props props(String s) {
        return Props.create(Echo.class, s);
    }

	@Override
	public Receive createReceive() {
		ReceiveBuilder builder = ReceiveBuilder.create();
		builder.match(Message.class, message -> {
			log.info("received an new message: {}.", message.value);
		});

		builder.matchAny(o -> log.warning("unknown message."));
		return builder.build();
	}

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("remote", ConfigFactory.load().getConfig("echo"));
		ActorRef echo = system.actorOf(Props.create(Echo.class), "echo");
	}
}
