package org.zeromem.lifecode.blockchain.contract.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.3.0.
 * contract address: 0xbc0d20e8e43e8a9592958376a7b1712fda7a6c86
 */
public final class MyToken extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b5b60018054600160a060020a03191633600160a060020a031690811790915560009081526020819052604090206305f5e10090555b5b610227806100546000396000f300606060405263ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416636392a51f811461005e5780638da5cb5b1461008f578063a0712d68146100be578063beabacc8146100d6575b600080fd5b341561006957600080fd5b61007d600160a060020a0360043516610100565b60405190815260200160405180910390f35b341561009a57600080fd5b6100a2610112565b604051600160a060020a03909116815260200160405180910390f35b34156100c957600080fd5b6100d4600435610121565b005b34156100e157600080fd5b6100d4600160a060020a0360043581169060243516604435610160565b005b60006020819052908152604090205481565b600154600160a060020a031681565b60015433600160a060020a0390811691161461013c57600080fd5b600154600160a060020a031660009081526020819052604090208054820190555b50565b60015433600160a060020a0390811691161461017b57600080fd5b600160a060020a038316600090815260208190526040902054819010156101a157600080fd5b600160a060020a03821660009081526020819052604090205481810110156101c857600080fd5b600160a060020a03808416600090815260208190526040808220805485900390559184168152208054820190555b5050505600a165627a7a72305820fa9ecacac070c8a92be95981a6fecc9785015f63c859e26b3cd73079d69a59140029\r\n";

    private MyToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private MyToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<Uint256> balancesOf(Address param0) {
        Function function = new Function("balancesOf", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> mint(Uint256 _amount) {
        Function function = new Function("mint", Arrays.<Type>asList(_amount), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> transfer(Address _from, Address _to, Uint256 _value) {
        Function function = new Function("transfer", Arrays.<Type>asList(_from, _to, _value), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<MyToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(MyToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<MyToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(MyToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static MyToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static MyToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
