package org.zeromem.lifecode.blockchain.contract.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
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
public final class PropertyOwner extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b5b60008054600160a060020a03191633600160a060020a03161790555b5b61053f8061003c6000396000f300606060405236156100755763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166340f42182811461007a57806341c0e1b5146100ae57806385edea13146100c3578063a9816b08146100eb578063ccb570e314610158578063ddc2a25114610192575b600080fd5b341561008557600080fd5b61009c600435600160a060020a03602435166101c4565b60405190815260200160405180910390f35b34156100b957600080fd5b6100c161023d565b005b34156100ce57600080fd5b61009c600435610265565b60405190815260200160405180910390f35b34156100f657600080fd5b61010460043560243561027a565b60405160208082528190810183818151815260200191508051906020019060200280838360005b838110156101445780820151818401525b60200161012b565b505050509050019250505060405180910390f35b341561016357600080fd5b61009c600435600160a060020a036024358116906044351661033f565b60405190815260200160405180910390f35b341561019d57600080fd5b6101a8600435610454565b604051600160a060020a03909116815260200160405180910390f35b6000806101d084610265565b915081905080156101e057600080fd5b600084815260016020819052604090912080549091810161020183826104b6565b916000526020600020900160005b8154600160a060020a038088166101009390930a9283029202191617905550600191909101905b5092915050565b60005433600160a060020a039081169116141561026257600054600160a060020a0316ff5b5b565b6000818152600160205260409020545b919050565b6102826104e0565b6000838152600160205260408120549081841061029d578193505b836040518059106102ab5750595b908082528060200260200182016040525b509250600090505b83811015610336576000858152600160205260409020805485840383019081106102ea57fe5b906000526020600020900160005b9054906101000a9004600160a060020a031683828151811061031657fe5b600160a060020a039092166020928302909101909101525b6001016102c4565b5b505092915050565b60008061034b85610265565b905080915083600160a060020a031633600160a060020a03161480156103715750600081115b80156103cd575060008581526001602052604090208054600160a060020a038616919060001984019081106103a257fe5b906000526020600020900160005b9054906101000a9004600160a060020a0316600160a060020a0316145b80156103eb575082600160a060020a031684600160a060020a031614155b15156103f657600080fd5b600085815260016020819052604090912080549091810161041783826104b6565b916000526020600020900160005b8154600160a060020a038088166101009390930a9283029202191617905550600191909101905b509392505050565b60008061046083610265565b90506000811161046f57600080fd5b60008381526001602052604090208054600019830190811061048d57fe5b906000526020600020900160005b9054906101000a9004600160a060020a031691505b50919050565b8154818355818115116104da576000838152602090206104da9181019083016104f2565b5b505050565b60206040519081016040526000815290565b61051091905b8082111561050c57600081556001016104f8565b5090565b905600a165627a7a7230582022dd1372c54fcd05f1a87f6597f3a12e981c988cd85da3bff1a0936c3d7eefe10029";

    private PropertyOwner(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private PropertyOwner(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> firstOwn(Uint256 id, Address owner) {
        Function function = new Function("firstOwn", Arrays.<Type>asList(id, owner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> kill() {
        Function function = new Function("kill", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> size(Uint256 id) {
        Function function = new Function("size", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<DynamicArray<Address>> getList(Uint256 id, Uint256 size) {
        Function function = new Function("getList", 
                Arrays.<Type>asList(id, size), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> transfer(Uint256 id, Address cur, Address next) {
        Function function = new Function("transfer", Arrays.<Type>asList(id, cur, next), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> getCurrent(Uint256 id) {
        Function function = new Function("getCurrent", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<PropertyOwner> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(PropertyOwner.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<PropertyOwner> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(PropertyOwner.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static PropertyOwner load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PropertyOwner(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static PropertyOwner load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PropertyOwner(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
