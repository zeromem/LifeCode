package org.zeromem.lifecode.paxos;

/**
 * Created by zeromem on 2017/9/26.
 */
public class Constants {
	// 全局proposer数量
	public static final Integer NUM_PROPOSER = 5;

	public static final Double ID_FRACTION_RESTRICT = 1000.0;

	public static final Integer NUM_ACCEPTOR = 5;

	public static final Integer NUM_MAJORITY = NUM_ACCEPTOR / 2 + 1;
}
