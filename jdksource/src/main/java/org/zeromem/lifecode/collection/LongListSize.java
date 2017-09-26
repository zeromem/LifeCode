package org.zeromem.lifecode.collection;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by zeromem on 2017/9/18.
 */
public class LongListSize {
	public static void main(String[] args) {
		Collection<Object> col = new LinkedList<>();
		// Collection.java中规定集合项目数量可以超过Integer.MAX_VALUE，但是size最大只返回Integer.MAX_VALUE
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			col.add(null);
			if (i % 1000000 == 0) {
				System.out.println(i / 1000000);
			}
		}

		col.add(null);

		System.out.println(col.size());
	}
}
