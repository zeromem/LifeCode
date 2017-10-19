package org.zeromem.lifecode.paxos;

import com.typesafe.config.ConfigFactory;

/**
 *
 * @author zeromem
 * @date 2017/9/26
 */
public class Constants {
	/**
	 * 全局acceptor数量
	 */
	public static final Integer NUM_ACCEPTOR;

	/**
	 * 用于将proposer的id转换为小数的分母
	 */
	public static final Double ID_FRACTION_RESTRICT = 1000.0;

	/**
	 * acceptor半数以上的数量
	 */
	public static final Integer NUM_MAJORITY;

	public static final String AKKA_SYS_PAXOS_PREFIX = "akka.tcp://paxos@";
	public static final String LITERAL_PROPOSER = "proposer";
	public static final String LITERAL_ACCEPTOR = "acceptor";
	public static final String LITERAL_ROLE = "role";

	static {
		NUM_ACCEPTOR = ConfigFactory.load().getStringList("paxos.acceptors").size();
		NUM_MAJORITY = NUM_ACCEPTOR / 2 + 1;
//		NUM_MAJORITY = 1;
	}
}
