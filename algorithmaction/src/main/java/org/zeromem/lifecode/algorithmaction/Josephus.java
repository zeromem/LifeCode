package org.zeromem.lifecode.algorithmaction;

/**
 * Created by zeromem on 2017/5/5.
 * 约瑟夫环问题
 * 整数的循环左移
 */
public class Josephus {
	public static void main(String[] args) {
		int a = 30;
		int b = Integer.highestOneBit(a);
		System.out.println(((a ^ b) << 1) + 1);
	}

	public static int intLeftRotate(int a) {
		return ((a ^ Integer.highestOneBit(a)) << 1) + 1;
	}


}
