package org.zeromem.lifecode.hack;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by zeromem on 2017/9/27.
 */
public class BigDecimalTest {
	public static void main(String[] args) {
		BigDecimal decimal = new BigDecimal(BigInteger.valueOf(10), 2);
		System.out.println(decimal);
		System.out.println(BigDecimal.valueOf(10.298234).scale());

		BigDecimal b1 = BigDecimal.valueOf(10.1);
		BigDecimal b2 = BigDecimal.valueOf(10.10);
		System.out.println(b1.equals(b2)); // true

		BigDecimal b3 = new BigDecimal("10.1");
		BigDecimal b4 = new BigDecimal("10.10");
		System.out.println(b3.equals(b4)); // false.
	}
}
