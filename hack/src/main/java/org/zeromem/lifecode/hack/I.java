package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/9/18.
 */
class InterfaceVariable {
	public static void main(String[] args) {
		System.out.println(I.b);
	}
}

public interface I {
	// 接口中的变量不能是private的
	// 接口中的变量默认就是public static final的，无访问修饰符时，不是package，而是public
	/*private*/ String a = "hello";
	String b = "world";
}
