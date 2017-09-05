package org.zeromem.lifecode.blockchain.contract;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.zeromem.lifecode.blockchain.contract.generated.MapArray;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.zeromem.lifecode.blockchain.Constants.remote;

/**
 * Created by zeromem on 2017/8/10.
 */
public class ContractHelloWorld {
	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, TimeoutException, CipherException {
		final Web3j web3j = Web3j.build(new HttpService(remote));
		Parity parity = Parity.build(new HttpService(remote));
		// gas price
		BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
		log("gas price: " + gasPrice);

//		parity.personalNewAccount("parallel").send();

		String account = parity.ethAccounts().send().getAccounts().get(0);
		TransactionManager manager = new ClientTransactionManager(web3j, account);
		parity.personalUnlockAccount(account, "parallel").send();
		log("unlock account.");

		/**
		 * 使用solidity web IDE + geth 部署新合约，使用web3j加载已有合约
		 * web IDE 编写contract，获取"Web3j deploy"模块中的代码，
		 * 在geth console中执行代码，调用miner.start()即可成功部署，获得合约地址。
		 */


//		String contractAddr = "0x37dc85ae239ec39556ae7cc35a129698152afe3c"; // token
//		String contractAddr = "0x3a020580345e79e223580d8d6a50e063667f19b5"; // simple storage
//		String contractAddr = "0x39c4d36e5b4d1d39ab884fbcef00edc1e48d091e"; // map(uint => uint[])
		String contractAddr = "0xe70e9d78b345dca6429ae7d444a5c99d4da060d8"; // map(uint => Foo[])
		MapArray contract = MapArray.load(
				contractAddr,
				web3j,
				manager,
				gasPrice,
				BigInteger.valueOf(100000)
		);
		log("load contract over. contract address: " + contract.getContractAddress());


//		TransactionReceipt receipt = contract.add(new Uint256(10), new Uint256(10)).get();
//		log("add method receipt: " + receipt.getBlockHash() + " " + receipt.getTransactionIndex());

		Uint256 receipt1 = contract.get(new Uint256(10), new Uint256(1)).get();
		log("get method result: " + (receipt1 == null ? null : receipt1.getValue()));


		TransactionReceipt receipt2 = contract.add(new Uint256(10), new Uint256(20)).get();
		log("add method receipt2: " + receipt2.getBlockHash() + " " + receipt2.getTransactionIndex());

		Uint256 receipt3 = contract.get(new Uint256(10), new Uint256(1)).get();
		log("after two insertion, get method result: " + receipt3.getValue());
	}


	public static void log(Object v) {
		System.out.println(new Date() + " " + v);
	}
}
