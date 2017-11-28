package org.zeromem.lifecode.algorithmaction;

/**
 * Created by zeromem on 2017/5/9.
 */
public class XORSwap {
	public static void main(String[] args) {
		int a = 10, b = 9;
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a + " " + b);
	}
}
