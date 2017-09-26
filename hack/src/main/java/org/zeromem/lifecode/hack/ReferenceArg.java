package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/9/8.
 */
public class ReferenceArg {

	private String name;

	public static void main(String args[]) {
		ReferenceArg m1 = new ReferenceArg();
		ReferenceArg m2 = new ReferenceArg();

		m1.name = m2.name = "m1";

		callMe(m1, m2);
		System.out.println(m1 + " & " + m2);
	}

	private static void callMe(ReferenceArg... m) {
		// m[0]指向m[1]，修改m[1]的name，则只修改了第2个参数的name，没有修改第1个参数
		m[0] = m[1];
//		m[1] = m[0];
		m[1].name = "new name";
	}

	@Override
	public String toString() {
		return name;
	}
}
