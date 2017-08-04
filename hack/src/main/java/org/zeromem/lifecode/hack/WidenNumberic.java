package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/8/1.
 * 数字类型自动宽化
 */
public class WidenNumberic {
	public static void main(String[] args) {
		int x = 0xa000;
		System.out.println(x + " --> " + (short) x);

		for (long i = 100000000L; i < 100000010L; i++) {
			float f = i;
			if ((long) f != i) {
				System.out.println("What! " + f + " != " + i);
			}
		}
	}
}

