package org.zeromem.lifecode.blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

import java.io.IOException;
import java.util.List;

import static org.zeromem.lifecode.blockchain.Constants.remote;

/**
 * Created by zeromem on 2017/8/16.
 * 新建账户
 */
public class NewAccount {
	public static void main(String[] args) throws IOException {
		Web3j web3j = Web3j.build(new HttpService(remote));
		Parity parity = Parity.build(new HttpService(remote));
		List<String> accountIds = parity.personalListAccounts().send().getAccountIds();
		System.out.println(accountIds);
		parity.personalNewAccount("parallel").send().getAccountId();
	}
}
