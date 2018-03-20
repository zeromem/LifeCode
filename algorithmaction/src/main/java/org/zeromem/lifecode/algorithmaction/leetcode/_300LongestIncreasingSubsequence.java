package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/20
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * <p>
 * Your algorithm should run in O(n2) complexity.
 * <p>
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class _300LongestIncreasingSubsequence {
    public static void main(String[] args) {
        _300LongestIncreasingSubsequence test = new _300LongestIncreasingSubsequence();
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(test.lengthOfLIS(nums));

    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        // dp[i] 以nums[i]为最后一个元素的最长子序列数量
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = 1;

        for (int i = 1; i < dp.length; i++) {
            int tmp = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    tmp = Math.max(tmp, dp[j]);
                }
            }
            dp[i] = Math.max(dp[i], tmp + 1);
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
