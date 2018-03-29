package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/29
 * <p>
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p>
 * Example 1
 * <p>
 * Input: [3,0,1]
 * Output: 2
 * Example 2
 * <p>
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 */
public class _268MissingNumber {
    public static void main(String[] args) {

    }

    public int missingNumber(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int len = nums.length;
        int exceptSum = len * (len + 1) / 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return exceptSum - sum;
    }
}
