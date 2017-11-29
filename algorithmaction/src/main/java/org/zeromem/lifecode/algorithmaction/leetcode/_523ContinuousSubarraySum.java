package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.HashSet;

/**
 * @author zeromem
 * @date 2017/11/29
 * <p>
 * <p>
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.
 * <p>
 * Example 1:
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2:
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 * Note:
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
public class _523ContinuousSubarraySum {
    public static void main(String[] args) {
        _523ContinuousSubarraySum test = new _523ContinuousSubarraySum();
        int[] nums = new int[]{1, 2, 3};
        int k = 5;
        System.out.println(test.checkSubarraySum(nums, k));
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n <= 1) {
            return false;
        }

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == 0 && nums[i + 1] == 0) {
                return true;
            }
        }
        if (k == 0) {
            return false;
        }
        k = Math.abs(k);

        int[] acc = new int[n];
        HashSet<Integer> set = new HashSet<>();

        acc[0] = nums[0];
        set.add(acc[0] % k);
        for (int i = 1; i < n; i++) {
            acc[i] = nums[i] + acc[i - 1];
            int mod = acc[i] % k;
            if (mod == 0 || set.contains(mod)) {
                return true;
            } else {
                set.add(mod);
            }
        }
        return false;
    }

}
