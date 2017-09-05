package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/9/5.
 */
public class LogicOperator {
	public static final boolean retTrue() {
		System.out.println("true");
		return true;
	}

	public static final boolean retFalse() {
		System.out.println("false");
		return false;
	}

	public static void main(String[] args) {
		System.out.println(retFalse() & retTrue());
		System.out.println("--------------------");
		System.out.println(retFalse() && retTrue());
	}
}
