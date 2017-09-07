package org.zeromem.lifecode.blockchain.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.zeromem.lifecode.blockchain.web.util.contract.MyToken;
import org.zeromem.lifecode.blockchain.web.util.contract.PropertyOwner;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by zeromem on 2017/8/16.
 */
public class Web3jConstant {
	public static final Logger log = LoggerFactory.getLogger(Web3jConstant.class);


	public static final String RPC_SERVER_ADDR = "http://10.9.59.138:18001";
	public static final String tokenAddress = "0x37dc85ae239ec39556ae7cc35a129698152afe3c";
	public static final String propertyAddress = "0xc7784403f00a1b6cffd1768874a1650a7c969622";

	public static final Web3j publicWeb3j = Web3j.build(new HttpService(RPC_SERVER_ADDR));
	public static final Parity publicParity = Parity.build(new HttpService(RPC_SERVER_ADDR));
	public static final BigInteger GAS_LIMIT = Contract.GAS_LIMIT;

	public static String COINBASE;
	public static TransactionManager manager;
	public static BigInteger GAS_PRICE;

	static {
		try {
			COINBASE = publicWeb3j.ethCoinbase().send().getAddress();
			manager = new ClientTransactionManager(publicWeb3j, COINBASE);
			GAS_PRICE = publicWeb3j.ethGasPrice().send().getGasPrice();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final MyToken token = MyToken.load(tokenAddress, publicWeb3j, manager, GAS_PRICE, GAS_LIMIT);
	public static final PropertyOwner property = PropertyOwner.load(propertyAddress, publicWeb3j, manager, GAS_PRICE, GAS_LIMIT);
}
