package org.zeromem.lifecode.algorithmaction.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zeromem
 * @date 2017/11/28
 * 1. Sort
 * 2. Find the length of longest subset
 * 3. Record the largest element of it.
 * 4. Do a loop from the largest element to nums[0], add every element belongs to the longest subset.
 * <p>
 * <p>
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * <p>
 * If there are multiple solutions, return any subset is fine.
 * <p>
 * Example 1:
 * <p>
 * nums: [1,2,3]
 * <p>
 * Result: [1,2] (of course, [1,3] will also be ok)
 * Example 2:
 * <p>
 * nums: [1,2,4,8]
 * <p>
 * Result: [1,2,4,8]
 */
public class _368LargestDivisibleSubset {
    public static void main(String[] args) {
        _368LargestDivisibleSubset test = new _368LargestDivisibleSubset();
        int[] nums = new int[]{1, 2, 3, 8};
        System.out.println(test.largestDivisibleSubset(nums));
    }

    /**
     * 1. Sort
     * 2. Find the length of longest subset
     * 3. Record the largest element of it.
     * 4. Do a loop from the largest element to nums[0], add every element belongs to the longest subset.
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        if (nums.length == 1) {
            res.add(nums[0]);
            return res;
        }

        Arrays.sort(nums);

        int n = nums.length;
        // dp[i]: 包含元素nums[i]的最大子集的size
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxPos = 0;
        for (int i = 1; i < n; i++) {
            if (dp[i] > dp[0]) {
                maxPos = i;
            }
        }

        for (int i = 0; i <= maxPos; i++) {
            if (nums[maxPos] % nums[i] == 0) {
                res.add(nums[i]);
            }
        }

        return res;
    }
}
