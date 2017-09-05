package org.zeromem.lifecode.blockchain.contract;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.zeromem.lifecode.blockchain.contract.generated.MyToken;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import static org.zeromem.lifecode.blockchain.Constants.*;

/**
 * Created by zeromem on 2017/9/4.
 */
public class MyTokenDemo {
	public static String contractAddress = "0xbc0d20e8e43e8a9592958376a7b1712fda7a6c86";

	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		String coinbase = publicWeb3j.ethCoinbase().send().getAddress();
		TransactionManager manager = new ClientTransactionManager(publicWeb3j, coinbase);
		publicParity.personalUnlockAccount(coinbase, "parallel");
		BigInteger gasLimit = new BigInteger("100000");

		MyToken contract = MyToken.load(contractAddress, publicWeb3j, manager, GAS_PRICE, gasLimit);
		System.out.println("contract load over.=======================");

		BigInteger coinbaseInitToken = contract.balancesOf(new Address(coinbase)).get().getValue();
		System.out.println("coinbase's init token: " + coinbaseInitToken);

		String add1 = "0x1";
		String add2 = "0x2";
		contract.transfer(new Address(coinbase), new Address(add1), new Uint256(100)).get();

		Uint256 a1Init = contract.balancesOf(new Address(add1)).get();
		System.out.println("after first transformation, a1's token: " + a1Init.getValue());

		contract.transfer(new Address(add1), new Address(add2), new Uint256(50)).get();

		Uint256 a1Value = contract.balancesOf(new Address(add1)).get();
		Uint256 a2Value = contract.balancesOf(new Address(add2)).get();

		System.out.println("after second transformation, a1's token: " + a1Value.getValue() + "; a2's token: " + a2Value.getValue());
	}
}
