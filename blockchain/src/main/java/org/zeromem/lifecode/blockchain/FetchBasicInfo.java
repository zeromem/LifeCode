package org.zeromem.lifecode.blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by zeromem on 2017/7/28.
 * using web3j to fetch basic info from private blockchain cluster.
 */
public class FetchBasicInfo {
	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		Web3j web3j = Web3j.build(new HttpService("http://10.9.59.138:18001"));

		EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
		System.out.println(blockNumber.getBlockNumber());
		System.out.println(blockNumber.getResult());

		Web3ClientVersion web3jClientVersion = web3j.web3ClientVersion().send();
		System.out.println(web3jClientVersion.getWeb3ClientVersion());

		Future<EthAccounts> future = web3j.ethAccounts().sendAsync();
		EthAccounts accounts = future.get();
		System.out.println(accounts.getAccounts());
	}
}
