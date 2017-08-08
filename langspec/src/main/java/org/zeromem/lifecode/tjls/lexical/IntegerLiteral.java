package org.zeromem.lifecode.tjls.lexical;

/**
 * Created by zeromem on 2017/7/19.
 */
public class IntegerLiteral {
	public static void main(String[] args) {
		int a = 071; // 8进制
		// int b = 091; // 编译错，0开头表示8进制，数字必须是0~7
		int c = 07_1; // 常量可以用_助记
		int d = 0_71;
		// int e = 071_; // _不得在八进制尾部
		// int f = 91_; // _不得在十进制尾部

		int big = 1234567890;
		float app = big;
		System.out.println(String.format("%.8f", app));
		System.out.println(big - (int)app);

		System.out.println("=====================");
		double d1 = 0, d2 = 0;
		Double dd = d1 / d2;
		System.out.println(dd);
		System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(dd)));
		d1 = 1;
		dd = d1 / d2;
		System.out.println(dd);
		System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(dd)));
		d1 = 0.00001;
		dd = d1 / d2;
		System.out.println(dd);
		System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(dd)));
	}
}
