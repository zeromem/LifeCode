package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/8/11.
 * int s = (bits >> 31) == 0) ? 1 : -1;
 * int e = (bits >> 23) & 0xff;
 * int m = (e == 0) ? (bits & 0x7fffff) << 1 : (bits & 0x7fffff) | 0x800000;
 * float result = s * m * Math.power(2, e - 150);
 */
public class CalcFloat {
	public static void main(String[] args) {
		float f = 10;
		int i = Float.floatToIntBits(f);

		int s = ((i >> 31) == 0) ? 1 : -1;
		int e = (i >> 23) & 0xff;
		int m = (e == 0) ? (i & 0x7fffff) << 1 : (i & 0x7fffff) | 0x800000;
		System.out.println(s);
		System.out.println(e);
		System.out.println(m);
		System.out.println(s * m * Math.pow(2, e - 150));
		System.out.println(s * m * (1 << (e - 150)));
	}
}
