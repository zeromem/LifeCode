package org.zeromem.lifecode.blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import rx.Observable;
import rx.Subscription;

/**
 * Created by zeromem on 2017/7/28.
 */
public class FetchBlocks {
	public static void main(String[] args) throws InterruptedException {
		Web3j web = Web3j.build(new HttpService("http://10.9.59.138:18001"));
		Observable<Transaction> observable = web.transactionObservable();
		observable.subscribe(transaction -> {
			System.out.println(transaction.getBlockNumber());
			System.out.println(transaction.getTransactionIndex());
			System.out.println(transaction.getInput());
		});
	}
}
