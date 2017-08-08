package org.zeromem.lifecode.hack;


import java.lang.reflect.Field;

/**
 * Created by zeromem on 2017/7/27.
 * hack String内部的数组
 */
public class HackStringCharArray {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		String s = new String("abc");
		s = s.intern();
		System.out.println(s);
		System.out.println(s.hashCode());
		System.out.println("-------------");

		// use getDeclaredField, not getField!
		Field cs = String.class.getDeclaredField("value");
		cs.setAccessible(true);
		cs.set(s, new char[]{'a', 'b', 'c', 'd'});
		System.out.println(s);
		System.out.println(s.hashCode());
		System.out.println("--------------");

		String n = s;
		System.out.println(n);
		System.out.println(n.hashCode());

		System.out.println(new String(s).hashCode());
		System.out.println(new String("abcd").hashCode());
	}
}
