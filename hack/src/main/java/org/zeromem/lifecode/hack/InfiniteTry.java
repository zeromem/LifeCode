package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/8/8.
 * 结合javap -c 字节码
 */
public class InfiniteTry {
	public static void foo() {
		try {
//			System.out.println("try");
			foo();
//			test();
		} catch (Throwable e) {
			e.printStackTrace();
//			System.out.println("catch");
			foo();
		} finally {
//			System.out.println("finally");
//			foo();
			test();
		}
	}

	public static void test() {

	}
	public static void main(String[] args) {
		foo();
	}
}
