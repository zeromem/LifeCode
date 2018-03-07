package org.zeromem.lifecode.algorithmaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zeromem on 2017/4/25.
 */
public class ThreeSum {
	private static final List<List<Integer>> result = new ArrayList<>();

	public static void main(String[] args) {
		int[] input = {-1, 0, 1, 2, -1, -4};
		ThreeSum test = new ThreeSum();
		System.out.println(test.threeSum(input));
	}


	public List<List<Integer>> threeSum(int[] list) {
		if (list == null || list.length <= 3) {
			return new ArrayList<>();
		}
		Arrays.sort(list);
		process(list, 0);
		for (int i = 1; i < list.length; i++) {
			if (list[i] == list[i - 1]) {
				continue;
			}
			process(list, i);
		}
		return result;
	}


	public static void process(int[] list, int position) {
	    int start = position + 1, end = list.length - 1;
		int sum = -list[position];
		while (start < end) {
			int curSum = list[start] + list[end];
			if (curSum == sum) {
				result.add(Arrays.asList(list[position], list[start], list[end]));
				while (start < end && list[start] == list[start + 1]) {
					start++;
				}
				while (end > start && list[end] == list[end - 1]) {
					end--;
				}
				start++;
				end--;
			} else if (curSum > sum) {
				end--;
			} else {
				start++;
			}
		}
	}
}
