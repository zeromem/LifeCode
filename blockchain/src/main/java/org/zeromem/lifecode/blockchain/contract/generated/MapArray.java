package org.zeromem.lifecode.blockchain.contract.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
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
 */
public final class MapArray extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b5b5b5b610180806100216000396000f300606060405263ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663669e48aa8114610048578063771602f714610073575b600080fd5b341561005357600080fd5b61006160043560243561008e565b60405190815260200160405180910390f35b341561007e57600080fd5b61008c6004356024356100c1565b005b60008281526020819052604081208054839081106100a857fe5b906000526020600020900160005b505490505b92915050565b60008281526020819052604090208054600181016100df8382610109565b916000526020600020900160005b60206040519081016040528481529190508151905550505b5050565b81548183558181151161012d5760008381526020902061012d918101908301610133565b5b505050565b61015191905b8082111561014d5760008155600101610139565b5090565b905600a165627a7a72305820953f0af67b0162471c1fcb4062e270c5847626e5a6c745bb472b4af772fc9fd20029";

    private MapArray(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private MapArray(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<Uint256> get(Uint256 id, Uint256 index) {
        Function function = new Function("get", 
                Arrays.<Type>asList(id, index), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> add(Uint256 id, Uint256 _x) {
        Function function = new Function("add", Arrays.<Type>asList(id, _x), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<MapArray> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(MapArray.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<MapArray> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(MapArray.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static MapArray load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MapArray(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static MapArray load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MapArray(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
