package org.zeromem.lifecode.paxos.message;

import org.zeromem.lifecode.paxos.Value;

import java.io.Serializable;

/**
 * Created by zeromem on 2017/10/10.
 */


public class Message implements Serializable {
	public static class ClientRequest {
		public final String key;
		public final Value value;

		public ClientRequest(String key, Value value) {
			this.key = key;
			this.value = value;
		}
	}

	public static class Prepare {
		public final Double uniq;

		public Prepare(Double uniq) {
			this.uniq = uniq;
		}
	}

	public static class PrepareResponseTimeout {

	}

	public static class PrepareOK {
		public final String key;
		public final Double uniq;
		public final Double AcceptedUniq;
		public final Value AcceptedValue;


		public PrepareOK(String key, Double uniq, Double acceptedUniq, Value acceptedValue) {
			this.key = key;
			this.uniq = uniq;
			AcceptedUniq = acceptedUniq;
			AcceptedValue = acceptedValue;
		}
	}

	public static class PrepareReject {
		public final String key;
		public final Double uniq;

		public PrepareReject(String key, Double uniq) {
			this.key = key;
			this.uniq = uniq;
		}
	}


	public static class Accept {
		public final String key;
		public final Double uniq;
		public final Value value;


		public Accept(String key, Double uniq, Value value) {
			this.key = key;
			this.uniq = uniq;
			this.value = value;
		}
	}
}




