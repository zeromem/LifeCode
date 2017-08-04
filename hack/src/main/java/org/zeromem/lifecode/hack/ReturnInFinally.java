package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/8/4.
 */
public class ReturnInFinally {
	public static void main(String[] args) {
		System.out.println(test());
	}

	public static int test() {
		try {
			return 1;

		} catch (Exception e) {
			return 2;
		} finally {
			try {
				return 3;
			} catch (Exception e) {
				return 4;
			} finally {
				return 5;
			}
		}
	}
}
