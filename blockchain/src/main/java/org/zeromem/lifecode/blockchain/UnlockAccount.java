package org.zeromem.lifecode.blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;

import java.io.IOException;

/**
 * Created by zeromem on 2017/8/2.
 */
public class UnlockAccount {
	public static final String url = "http://10.9.59.138:18001";
	public static void main(String[] args) throws IOException {
		final Parity parity = Parity.build(new HttpService(url));
		Web3j web3j = Web3j.build(new HttpService(url));
		web3j.ethAccounts().send().getAccounts().forEach(account -> {
			try {
				PersonalUnlockAccount unlock = parity.personalUnlockAccount(account, "parallel").send();
				System.out.println(account + " -> " + unlock.accountUnlocked());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
