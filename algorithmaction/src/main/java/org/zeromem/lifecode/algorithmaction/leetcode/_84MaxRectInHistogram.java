package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * Created by zeromem on 2017/5/15.
 */
public class _84MaxRectInHistogram {
	public static void main(String[] args) {
		_84MaxRectInHistogram test = new _84MaxRectInHistogram();
//		int[] a = {2, 1, 5, 6, 2, 3};
		int[] a = {1, 1};
		System.out.println(test.largestRectangleArea(a));

	}


	public int largestRectangleArea(int[] heights) {
		if (heights == null || heights.length == 0) {
			return 0;
		}

		if (heights.length == 1) {
			return heights[0];
		}

		int res = 0;
		for (int i = 0; i < heights.length; i++) {
			// 寻找波峰
			if (i + 1 < heights.length && heights[i] <= heights[i + 1]) {
				continue;
			}

			// 从波峰向前扫描到j=0，更新res
			res = res > heights[i] ? res : heights[i];
			int minHeight = heights[i];
			for (int j = i - 1; j >= 0; j--) {
				if (heights[j] < minHeight) {
					minHeight = heights[j];
				}
				int cur = minHeight * (i - j + 1);
				if (cur > res) {
					res = cur;
				}
			}
		}
		return res;
	}
}
