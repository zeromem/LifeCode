package org.zeromem.lifecode.blockchain;

import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

import java.io.IOException;
import java.util.Arrays;

import static org.zeromem.lifecode.blockchain.UnlockAccount.url;

/**
 * Created by zeromem on 2017/8/2.
 */
public class CreateAccount {
	public static void main(String[] args) throws IOException {
		Parity parity = Parity.build(new HttpService(url));
		Arrays.stream(new int[4]).forEach((x) -> {
			try {
				System.out.println(parity.personalNewAccount("parallel").send().getAccountId());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
