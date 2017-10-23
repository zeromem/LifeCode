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

        @Override
        public String toString() {
            return "ClientRequest{" +
                    "key='" + key + '\'' +
                    ", value=" + value +
                    '}';
        }
    }


    /**
     * Proposer在prepare阶段发送给Acceptor的Prepare
     */
	public static class Prepare extends Message {
		public final String key;
		public final Double uniq;

		public Prepare(String key, Double uniq) {
			this.key = key;
			this.uniq = uniq;
		}

        @Override
        public String toString() {
            return "Prepare{" +
                    "key='" + key + '\'' +
                    ", uniq=" + uniq +
                    '}';
        }
    }

    /**
     * Proposer在prepare阶段内部使用的PrepareTimeout
     */
	public static class PrepareTimeout extends Message {
		public final String key;
		public final Double uniq;


		public PrepareTimeout(String key, Double uniq) {
			this.key = key;
			this.uniq = uniq;
		}

        @Override
        public String toString() {
            return "PrepareTimeout{" +
                    "key='" + key + '\'' +
                    ", uniq=" + uniq +
                    '}';
        }
    }

    /**
     * Acceptor在prepare阶段回复给Proposer的确认
     */
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

        @Override
        public String toString() {
            return "PrepareOK{" +
                    "key='" + key + '\'' +
                    ", uniq=" + uniq +
                    ", AcceptedUniq=" + AcceptedUniq +
                    ", AcceptedValue=" + AcceptedValue +
                    '}';
        }
    }

    /**
     * Acceptor在prepare阶段回复给Proposer的拒绝
     */
	public static class PrepareReject extends Message {
		public final String key;
		public final Double uniq;

		public PrepareReject(String key, Double uniq) {
			this.key = key;
			this.uniq = uniq;
		}

        @Override
        public String toString() {
            return "PrepareReject{" +
                    "key='" + key + '\'' +
                    ", uniq=" + uniq +
                    '}';
        }
    }


    /**
     * Proposer在accept阶段发送给Acceptor的accept消息
     */
	public static class Accept extends Message {
		public final String key;
		public final Double uniq;
		public final Value value;


		public Accept(String key, Double uniq, Value value) {
			this.key = key;
			this.uniq = uniq;
			this.value = value;
		}

        @Override
        public String toString() {
            return "Accept{" +
                    "key='" + key + '\'' +
                    ", uniq=" + uniq +
                    ", value=" + value +
                    '}';
        }
    }

    /**
     * Acceptor在accept阶段回复给proposer的确认
     */
	public static class AcceptOk extends Message {
		public final String key;
		public final Double uniq;
		public final Value value;

		public AcceptOk(String key, Double uniq, Value value) {
			this.key = key;
			this.uniq = uniq;
			this.value = value;
		}

        @Override
        public String toString() {
            return "AcceptOk{" +
                    "key='" + key + '\'' +
                    ", uniq=" + uniq +
                    ", value=" + value +
                    '}';
        }
    }

    /**
     * Acceptor在accept阶段回复给proposer的拒绝
     */
	public static class AcceptReject extends Message {
		public final String key;
		public final Double uniq;

		public AcceptReject(String key, Double uniq) {
			this.key = key;
			this.uniq = uniq;
		}

        @Override
        public String toString() {
            return "AcceptReject{" +
                    "key='" + key + '\'' +
                    ", uniq=" + uniq +
                    '}';
        }
    }

    /**
     * Proposer在accept阶段内部使用的AcceptTimeout
     */
	public static class AcceptTimeout extends Message {
		public final String key;
		public final Double uniq;

		public AcceptTimeout(String key, Double uniq) {
			this.key = key;
			this.uniq = uniq;
		}

        @Override
        public String toString() {
            return "AcceptTimeout{" +
                    "key='" + key + '\'' +
                    ", uniq=" + uniq +
                    '}';
        }
    }

    /**
     * decide阶段Proposer发送给Learner的消息
     */
	public static class Decide extends Message {
		public final String key;
		public final Double uniq;
		public final Value value;


		public Decide(String key, Double uniq, Value value) {
			this.key = key;
			this.uniq = uniq;
			this.value = value;
		}

        @Override
        public String toString() {
            return "Decide{" +
                    "key='" + key + '\'' +
                    ", uniq=" + uniq +
                    ", value=" + value +
                    '}';
        }
    }

    /**
     * client从learner获取数据的请求
     */
	public static class Fetch extends Message {
        public final String key;

        public Fetch(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "Fetch{" +
                    "key='" + key + '\'' +
                    '}';
        }
    }
}




