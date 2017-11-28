package org.zeromem.lifecode.algorithmaction;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by zeromem on 2017/3/21.
 */
public class Collatz {
	public static void main(String[] args) {
		for (int i = 1; i < 1000; i++) {
			int hard = (i << 2) + 3;
			List<Integer> res = collatz(hard);
//			System.out.println(res.size() + " - " + Lists.reverse(collatz(hard)));
		}
	}

	public static List<Integer> collatz(int x) {
		List<Integer> res = new LinkedList<>();
		res.add(x);
		if (x == 1) {
			return res;
		} else if (x % 2 == 0) {
			res.addAll(collatz(x / 2));
			return res;
		} else {
			res.addAll(collatz(3 * x + 1));
			return res;
		}
	}
}
