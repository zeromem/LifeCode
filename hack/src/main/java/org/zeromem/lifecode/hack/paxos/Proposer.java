package org.zeromem.lifecode.hack.paxos;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import static org.zeromem.lifecode.hack.paxos.Constants.NUM_PROPOSER;

/**
 * Created by zeromem on 2017/9/26.
 */
public class Proposer extends AbstractActor {
	// log of this's actor
	private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	// props to create this actor
	static public Props props(final Integer id, final Integer number) {
		return Props.create(Proposer.class, () -> new Proposer(id, number));
	}

	// main logic of this actor
	@Override
	public Receive createReceive() {
		return receiveBuilder().build();
	}

	public final Integer id;
	private Integer number;

	// constructor of this actor
	/**
	 *
	 * @param id assigned proposer id.
	 * @param number init proposal number.
	 */
	public Proposer(Integer id, Integer number) {
		this.id = id;
		this.number = number;
	}




	/////// proposer's messages. /////////////

	// Prepare request with number n from proposer i.
	public static class Prepare {
		public final Double NUMBER;

		public Prepare(Integer number, Integer id) {
			this.NUMBER = number + id.doubleValue() / NUM_PROPOSER;
		}
	}

	// a proposal.
	public static class Proposal {
		public final Double NUMBER;
		public final Object VALUE;

		public Proposal(Double number, Object value) {
			NUMBER = number;
			VALUE = value;
		}
	}

	public static class Accept {
		public final Integer n;
		public final Integer v;

		public Accept(Integer n, Integer v) {
			this.n = n;
			this.v = v;
		}
	}
}
