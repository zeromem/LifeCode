package org;

import java.io.IOException;

import static org.zeromem.lifecode.blockchain.web.util.Web3jConstant.publicParity;

/**
 * Created by zeromem on 2017/9/5.
 */
public class NewAccountTest {
	public static void main(String[] args) throws IOException {
		System.out.println(publicParity.personalNewAccount("parallel").send().getAccountId());
	}
}
