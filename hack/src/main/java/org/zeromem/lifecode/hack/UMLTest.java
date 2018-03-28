package org.zeromem.lifecode.hack;


/**
 * Created by zeromem on 2017/9/18.
 * 定义不同的类，查看对应的UML类图
 */
public class UMLTest {
	public static void main(String[] args) {
		AC1.AC1_s = "hello";
	}
}

abstract class AC1 {
	public static String AC1_s = "AC1_s";

	abstract public void AC1_am1();


	public void AC1_m2() {
	}
}

// 接口可以extends接口，不能继承类（包括抽象类）
interface I1 extends I {
	String I1_s = "I1_s";
}

class C extends AC1 implements I1 {
	@Override
	public void AC1_am1() {

	}

	public static final int a = 1;
	public int b = 2;

}

