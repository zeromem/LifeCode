package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/8/1.
 * 严格的浮点数运算
 */
 public class StrictFPMode {
	public static void main(String[] args) {
		NonStrictC.calc();
		StrictC.calc();

	}
}

class NonStrictC {
	public static void calc() {
		double a = (1.2 - 0.4);
		System.out.println(a);
	}
}


strictfp class StrictC {
	public static void calc() {
		double a = (1.2 - 0.4);
		System.out.println(a);
	}
}
