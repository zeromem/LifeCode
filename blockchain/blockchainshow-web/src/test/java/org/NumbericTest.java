package org;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.utils.Numeric;

/**
 * Created by zeromem on 2017/9/5.
 */
public class NumbericTest {
	public static void main(String[] args) {
		String a = "0x3c0bee3d6a96165f80e9c85f56b5d08c44d38f76974947ffdc50ef693d60ffb9";
		System.out.println(new Uint256(Numeric.toBigInt(a)).getValue());
	}
}
