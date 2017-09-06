package org;

import org.junit.Test;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.zeromem.lifecode.blockchain.web.util.Web3jConstant.publicParity;
import static org.zeromem.lifecode.blockchain.web.util.Web3jConstant.token;

/**
 * Created by zeromem on 2017/9/5.
 */
public class BlockchainTest {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		String owner = token.owner().get().toString();
		System.out.println(owner);
	}

	@Test
	public void testUnlock() throws IOException {
		PersonalUnlockAccount res = publicParity.personalUnlockAccount(
				"0xa4bfddff72ec05a0de37bf8043341d0ff55a57ab",
				"parallel"
		).send();

		System.out.println(res.getError());
		System.out.println(res.getResult());
	}

	@Test
	public void testMiner() throws IOException {
		// ZEROMEM miner.start()是我自己写的,可以正常启动miner，但是response还不正确
		publicParity.minerStart();
	}
}
