package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/9/8.
 */
public class StrToIntTest {
	private int num = 0;

	public static void main(String args[]) {
		StrToIntTest m1 = new StrToIntTest();
		System.out.print(m1.strToInt("10"));
		System.out.println(m1.strToInt(m1.num + "1"));
	}

	public int strToInt(String num) {
		this.num = Integer.parseInt(num);
		return this.num;
	}
}
