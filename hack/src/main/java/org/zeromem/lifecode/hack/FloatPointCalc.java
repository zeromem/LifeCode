package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/8/1.
 * 浮点数计算
 */
public class FloatPointCalc {
	public static void main(String[] args) {
		specialTest();
	}

	public static void binTest() {
		System.out.println("2d / 0d");
		double a = 1d / 0.0000000001d;
		System.out.println("value: " + a);
		System.out.println("hex value: " + Double.toHexString(a));
		System.out.println("bin string: " + Long.toBinaryString(Double.doubleToRawLongBits(a)));
		System.out.println("bin string length: " + Long.toBinaryString(Double.doubleToRawLongBits(a)).length());

		System.out.println("=======================================");

		System.out.println("2d / -0d");
		double b = 1d / -0d;
		System.out.println("value: " + b);
		System.out.println("hex value: " + Double.toHexString(b));
		System.out.println("bin string: " + Long.toBinaryString(Double.doubleToRawLongBits(b)));
		System.out.println("bin string length: " + Long.toBinaryString(Double.doubleToRawLongBits(b)).length());
	}

	public static void specialTest() {
		double neg = -1.0, zero = 0.0, one = 1.0, little = -0.01;

		// √(-N) not a number
		double sqrt = Math.sqrt(neg);
		System.out.println("√(-N) not a number --- " + sqrt);

		double nan = sqrt;

		// 0.0/0.0, not a number.
		double zeroDividedByZero = zero / zero;
		System.out.println("0.0 / 0.0, not a number --- " + zeroDividedByZero);

		// other positive divided by zero, infinity.
		double posDividedByZero = 0.001 / zero;
		System.out.println("other positive divided by zero, infinity --- " + posDividedByZero);

		// other negative divided by zero, negative infinity.
		double negDividedByZero = little / zero;
		System.out.println("other negative divided by zero, negative infinity --- " + negDividedByZero);

		// any operation on NaN, not a number.
		System.out.println("any operation on NaN, not a number --- ");
		System.out.println("NaN + 1 --- " + nan + 1);
		System.out.println("NaN + 0 --- " + 0 * nan);
		System.out.println("NaN - NaN --- " + (nan - nan));
		System.out.println("NaN - NaN --- " + nan / nan);

		// any condition contains NaN, return false.
		System.out.println("any condition contains NaN, return false.");
		System.out.println(nan > 10);
		System.out.println(nan == nan);
		System.out.println(nan == 0);
		System.out.println(nan == posDividedByZero);
	}
}
