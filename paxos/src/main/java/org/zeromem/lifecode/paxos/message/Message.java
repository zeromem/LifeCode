package org.zeromem.lifecode.paxos.message;

import org.zeromem.lifecode.paxos.Value;

import java.io.Serializable;

/**
 *
 * @author zeromem
 * @date 2017/10/10
 */

public class Message implements Serializable {
	/**
	 * 客户端发送给proposer的请求
	 */
	public static class ClientRequest extends Message {
		public final String key;
		public final Value value;
		public ClientRequest(String key, Value value) {
			this.key = key;
			this.value = value;
		}
	}


	///////////// prepare阶段相关请求 ////////////////////

	public static class Prepare extends Message {
		public final String key;
		public final Double uniq;

		public Prepare(String key, Double uniq) {
			this.key = key;
			this.uniq = uniq;
		}
	}

	public static class PrepareTimeout extends Message {
		public final String key;
		public final Double uniq;


		public PrepareTimeout(String key, Double uniq) {
			this.key = key;
			this.uniq = uniq;
		}
	}

	public static class PrepareOK extends Message {
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

	public static class PrepareReject extends Message {
		public final String key;
		public final Double uniq;

		public PrepareReject(String key, Double uniq) {
			this.key = key;
			this.uniq = uniq;
		}
	}


	//////////// Accept阶段相关请求 //////////////////
	public static class Accept extends Message {
		public final String key;
		public final Double uniq;
		public final Value value;


		public Accept(String key, Double uniq, Value value) {
			this.key = key;
			this.uniq = uniq;
			this.value = value;
		}
	}

	public static class AcceptOK extends Message {
		public final String key;
		public final Double uniq;
		public final Value value;

		public AcceptOK(String key, Double uniq, Value value) {
			this.key = key;
			this.uniq = uniq;
			this.value = value;
		}
	}

	public static class AcceptReject extends Message {
		public final String key;
		public final Double uniq;

		public AcceptReject(String key, Double uniq) {
			this.key = key;
			this.uniq = uniq;
		}
	}

	public static class AcceptTimeout extends Message {
		public final String key;
		public final Double uniq;

		public AcceptTimeout(String key, Double uniq) {
			this.key = key;
			this.uniq = uniq;
		}
	}

	public static class Decide extends Message {
		public final String key;
		public final Double uniq;
		public final Value value;


		public Decide(String key, Double uniq, Value value) {
			this.key = key;
			this.uniq = uniq;
			this.value = value;
		}
	}

}




