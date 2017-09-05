package org.zeromem.lifecode.blockchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.MinerStart;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.zeromem.lifecode.blockchain.contract.generated.PropertyOwner;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import static org.zeromem.lifecode.blockchain.Constants.remote;

/**
 * Created by zeromem on 2017/8/15.
 * 产权转移合约 0xa9a0d0143a9e7de7b3dd8a34b25b5237b3ece407
 */
public class PropertyTransfer {
	public static final Logger log = LoggerFactory.getLogger(PropertyTransfer.class);

	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		Web3j web3j = Web3j.build(new HttpService(remote));
		BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
		BigInteger gasLimit = Contract.GAS_LIMIT;
		// buggy..
		MinerStart start = web3j.minerStart().send();

		Parity parity = Parity.build(new HttpService(remote));
		String account = parity.ethAccounts().send().getAccounts().get(0);
		parity.personalUnlockAccount(account, "parallel");

		TransactionManager manager = new ClientTransactionManager(web3j, account);

		String address = "0xa9a0d0143a9e7de7b3dd8a34b25b5237b3ece407";
		PropertyOwner contract = PropertyOwner.load(address, web3j, manager, gasPrice, gasLimit);
		log.info("contract load over.");
		contract.kill();


		TransactionReceipt receipt = contract.firstOwn(new Uint256(1), new Address("0x100")).get();
		BigInteger blkNumber = receipt.getBlockNumber();
		String txHash = receipt.getTransactionHash();
		Transaction transaction = web3j.ethGetTransactionByHash(txHash).send().getTransaction().get();
		int v = transaction.getV();
		log.info("tx.v of firstOwn(): {}", v);

		// key not exist, will return null.
		Address owner = contract.getCurrent(new Uint256(1)).get();
		log.info("current owner of 1: {}", owner);
	}
}
