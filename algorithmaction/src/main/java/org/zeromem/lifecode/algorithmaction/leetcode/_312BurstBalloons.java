package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2017/11/15
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * <p>
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Note:
 * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * <p>
 * Example:
 * <p>
 * Given [3, 1, 5, 8]
 * <p>
 * Return 167
 * nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class _312BurstBalloons {
    public static void main(String[] args) {
        _312BurstBalloons test = new _312BurstBalloons();
        int[] nums = new int[]{3, 1, 5, 8};
        System.out.println(test.maxCoins(nums));
    }

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        // highest score between nums[i,...,j]
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
            if (i - 1 >= 0) {
                dp[i][i] *= nums[i - 1];
            }
            if (i + 1 < n) {
                dp[i][i] *= nums[i + 1];
            }
        }

        for (int gap = 1; gap <= n - 1; gap++) {
            for (int start = 0; start + gap < n; start++) {
                int end = start + gap;
                dp[start][end] = 0;
                for (int lastBurst = start; lastBurst <= end; lastBurst++) {
                    // last burst's score.
                    int sum = nums[lastBurst];
                    sum *= (start - 1) >= 0 ? nums[start - 1] : 1;
                    sum *= (end + 1) < n ? nums[end + 1] : 1;

                    // two sides' score.
                    if (lastBurst == start) {
                        sum += dp[start + 1][end];
                    } else if (lastBurst == end) {
                        sum += dp[start][end - 1];
                    } else {
                        sum += dp[start][lastBurst - 1] + dp[lastBurst + 1][end];
                    }

                    // update.
                    if (sum > dp[start][end]) {
                        dp[start][end] = sum;
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[0][n - 1];
    }

}
