package org.zeromem.lifecode.blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.tx.Contract;
import org.zeromem.lifecode.blockchain.contract.generated.MyToken;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by zeromem on 2017/8/14.
 */
public class Constants {
	public static final String remote = "http://10.9.59.138:18001";
	public static final String tokenAddress = "0xbc0d20e8e43e8a9592958376a7b1712fda7a6c86";

	public static final Web3j publicWeb3j = Web3j.build(new HttpService(remote));
	public static final Parity publicParity = Parity.build(new HttpService(remote));

	public static final BigInteger GAS_PRICE;
	static {
		BigInteger price;
		try {
			price = publicWeb3j.ethGasPrice().send().getGasPrice();
		} catch (IOException e) {
			price = Contract.GAS_PRICE;
			e.printStackTrace();
		}
		GAS_PRICE = price;
	}
}
