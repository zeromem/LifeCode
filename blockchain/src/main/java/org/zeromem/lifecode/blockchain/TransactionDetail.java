package org.zeromem.lifecode.blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

import static org.zeromem.lifecode.blockchain.Constants.remote;

/**
 * Created by zeromem on 2017/8/15.
 */
public class TransactionDetail {
	public static void main(String[] args) throws IOException {
		Web3j web3j = Web3j.build(new HttpService(remote));
		Request<?, EthTransaction> request = web3j.ethGetTransactionByHash("0x8951ea98df042b66b79fc987aeef0f593f591720191972fefd265f7e5b802562");
		Transaction transaction = request.send().getTransaction().get();
		System.out.println(transaction.getR());
		System.out.println(transaction.getRaw());
		System.out.println(transaction.getCreates());
		System.out.println(transaction.getFrom());
		System.out.println(transaction.getGas());
		System.out.println(transaction.getS());
		System.out.println(transaction.getTo());
	}
}
