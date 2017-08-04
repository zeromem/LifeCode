package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/8/1.
 * 浮点数计算
 */
public class FloatPointCalc {
	public static void main(String[] args) {
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
}
