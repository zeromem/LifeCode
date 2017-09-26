package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/9/6.
 */
public class StringIntern {
	public static void main(String[] args) {
		testNew();
	}

	static void testIntern() {
		String s1 = "zeromem";
		String s2 = new String("zeromem");
		String s3 = "zero" + "mem";
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		System.out.println(s1 == s1.intern());

	}

	static void testNew() {
		String s1 = new String("sss");
		String s2 = new String("sss");

		System.out.println(s1 == s2);
		System.out.println(s1 == s2.intern());
		System.out.println(s1.intern() == s2);
		System.out.println(s1.intern() == s2.intern());
	}
}
