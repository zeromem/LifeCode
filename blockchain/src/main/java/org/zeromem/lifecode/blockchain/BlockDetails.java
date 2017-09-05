package org.zeromem.lifecode.blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.DbPutString;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

import java.io.IOException;
import java.math.BigInteger;

import static org.zeromem.lifecode.blockchain.UnlockAccount.url;

/**
 * Created by zeromem on 2017/8/11.
 */
public class BlockDetails {
	public static void main(String[] args) throws IOException {
		Web3j web3j = Web3j.build(new HttpService(url));
		Parity parity = Parity.build(new HttpService(url));
		DbPutString send = parity.dbPutString("db", "k1", "Hello").send();
		System.out.println(send.getError().getMessage());
//		System.out.println(web3j.dbGetString("db", "k1").send().getStoredValue());

	}
}
