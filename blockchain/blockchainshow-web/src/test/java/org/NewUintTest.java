package org;

import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

/**
 * Created by zeromem on 2017/9/5.
 */
public class NewUintTest {
	public static void main(String[] args) {
		Uint256 a = new Uint256(new BigInteger("0x12", 16));
		System.out.println(a.getValue());

	}
}
