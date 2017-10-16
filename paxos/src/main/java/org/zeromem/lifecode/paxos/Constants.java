package org.zeromem.lifecode.paxos;

/**
 * Created by zeromem on 2017/9/26.
 */
public class Constants {
	// 全局proposer数量
	public static final Integer NUM_PROPOSER = 5;

	public static final Double ID_FRACTION_RESTRICT = 1000.0;

	public static final Integer NUM_ACCEPTOR = 5;

	// todo 1 is just for test.
	public static final Integer NUM_MAJORITY = /*NUM_ACCEPTOR / 2 +*/ 1;

	public static final String AKKA_SYS_PAXOS_PREFIX = "akka.tcp://paxos@";
}
