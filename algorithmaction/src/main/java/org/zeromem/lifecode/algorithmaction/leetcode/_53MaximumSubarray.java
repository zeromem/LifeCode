package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/12/8
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * <p>
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * dp[i]表示以第i个元素为结尾的最大子数组。则有两种赋值情况
 * a. dp[i - 1] <  0,则不应将前面的任何子数组相连（因为以i-1结尾的最大和也是负数）
 * b. dp[i - 1] >= 0,可以连接以i-1为结尾的最大子数组，且dp[i] = nums[i] + dp[i - 1]
 */
public class _53MaximumSubarray {
    public static void main(String[] args) {
        _53MaximumSubarray test = new _53MaximumSubarray();
        int[] nums = new int[]{-2, -1};
        System.out.println(test.maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {
        // 用动态规划解
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        // dp[i]: 以nums[i]为结尾的最大子数组和
        int[] dp = new int[n];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            if (dp[i] > res) {
                res = dp[i];
            }
        }
        return res;
    }
}
