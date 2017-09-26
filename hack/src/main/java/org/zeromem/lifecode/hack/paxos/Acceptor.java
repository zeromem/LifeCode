package org.zeromem.lifecode.hack.paxos;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import static org.zeromem.lifecode.hack.paxos.Proposer.*;

/**
 * Created by zeromem on 2017/9/26.
 */
public class Acceptor extends AbstractActor {
	private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
	private Integer maxN;
	private Integer value;


	static public Props props() {
		return Props.create(Acceptor.class, () -> new Acceptor());
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Prepare.class, (n) -> {
		}).build();

	}

	////////// acceptor's messages:
	public static class Ack {
		public final Integer n;
		public final Integer v;
		public final Integer nv;

		public Ack(Integer n, Integer v, Integer nv) {
			this.n = n;
			this.v = v;
			this.nv = nv;
		}
	}

	public static class NAck {
		public final Integer n;
		public final Integer nv;

		public NAck(Integer n, Integer nv) {
			this.n = n;
			this.nv = nv;
		}
	}
}
