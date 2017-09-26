package org.zeromem.lifecode.hack;

import java.util.*;

/**
 * Created by zeromem on 2017/9/8.
 */
public class MapCompute {
	Map<String, Object> map = new HashMap<>();

	public static void main(String[] args) {
//		test(null);
//		test("hello");
//		System.exit(1);

		Map<String, Object> collection = new TreeMap<>();

		System.out.println(collection.compute("foo", (k, v) -> (v == null) ? new ArrayList<Object>() : ((List) v).add("bar")));
		System.out.println(collection.compute("foo", (k, v) -> (v == null) ? new ArrayList<Object>() : ((List) v).add("ber")));
	}

	public static void test(String input) {
		Object a = (input == null ? 10D : false);
		System.out.println(a.getClass().getName());
	}
}
