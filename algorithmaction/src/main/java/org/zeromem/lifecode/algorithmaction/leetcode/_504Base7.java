package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * Created by zeromem on 2017/3/8.
 * Given an integer, return its base 7 string representation.
 * <p>
 * Example 1:
 * Input: 100
 * Output: "202"
 * Example 2:
 * Input: -7
 * Output: "-10"
 * Note: The input will be in range of [-1e7, 1e7].
 */
public class _504Base7 {
	public static int[] Ps = {
			1,
			7,
			49,
			343,
			2401,
			16807,
			117649,
			823543,
			5764801,
			40353607
	};

	public static void main(String[] args) {
		_504Base7 test = new _504Base7();
		int num = 0;
		System.out.println(test.convertToBase7(num));
	}

	/**
	 * y = a_0 * 7^0 + a_1 * 7^1 + .. + a_k * 7^k
	 * 先求k，再求a_k；然后是k-1, a_k-1,...
	 *
	 * @param num
	 * @return
	 */
	public String convertToBase7(int num) {
		if (num == 0) return "0";
		StringBuilder sb = new StringBuilder();
		if (num < 0) {
			sb.append('-');
			num = -num;
		}
		int[] as = new int[10];
		for (int i = 9; i > 0; i--) {
			as[i] = num / Ps[i];
			num -= as[i] * Ps[i];
		}
		as[0] = num;
		int i = 9;
		while (as[i--] == 0);
		for (i++; i >= 0; i--) {
			sb.append(as[i]);
		}
		return sb.toString();
	}


}
