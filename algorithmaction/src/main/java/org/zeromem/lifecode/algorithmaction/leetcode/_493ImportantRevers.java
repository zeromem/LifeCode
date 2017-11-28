package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * Created by zuochuang on 2017/4/4.
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

 You need to return the number of important reverse pairs in the given array.

 Example1:

 Input: [1,3,2,3,1]
 Output: 2
 Example2:

 Input: [2,4,3,5,1]
 Output: 3
 Note:
 The length of the given array will not exceed 50,000.
 All the numbers in the input array are in the range of 32-bit integer.
 */
public class _493ImportantRevers {
	public static void main(String[] args) {

	}

	public int reversePairs(int[] nums) {
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int base = nums[j] << 1;
				if (base < 0) {
					continue;
				}
				if (nums[i] > base) {
					res++;
				}

			}
		}
		return res;
	}




}
