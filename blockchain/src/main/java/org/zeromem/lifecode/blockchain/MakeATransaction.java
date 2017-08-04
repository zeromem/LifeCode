package org.zeromem.lifecode.blockchain;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionTimeoutException;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static org.zeromem.lifecode.blockchain.UnlockAccount.url;

/**
 * Created by zeromem on 2017/7/28.
 */
public class MakeATransaction {
	public static void main(String[] args) throws IOException, CipherException, TransactionTimeoutException, InterruptedException {
		final Web3j rpc = Web3j.build(new HttpService(url));
		final Parity parity = Parity.build(new HttpService(url));
		Arrays.stream(new String[]{
				"0x21ff750b30afe85179539c54ee8dcf4e719a754c",
				"0xbafa9f629489ac15d222a8019150accd4c6ff94d"
		}).forEach(addr -> {
			try {
				System.out.println("unlock " + addr + " -> " + parity.personalUnlockAccount(addr, "parallel").send().accountUnlocked());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});


		Response response = rpc.ethSendTransaction(new Transaction(
				"0x21ff750b30afe85179539c54ee8dcf4e719a754c",
				BigInteger.valueOf(106),
				null,
				null,
				"0xbafa9f629489ac15d222a8019150accd4c6ff94d",
				Convert.toWei("1.0", Convert.Unit.ETHER).toBigInteger(),
				null
		)).send();
		if (response.hasError()) {
			System.err.println(response.getError().getMessage());
			System.err.println(response.getError().getData());
		}


	/*	Optional<TransactionReceipt> optional = rpc.ethGetTransactionReceipt(hash).send().getTransactionReceipt();
		optional.map(receipt -> {
			System.out.println(receipt.getBlockHash());
			System.out.println(receipt.getBlockNumber());
			System.out.println(receipt.getTransactionIndex());
			return 1;
		});*/
	}
}
