package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/12/5
 * <p>
 * Given an array nums of integers, you can perform operations on the array.
 * <p>
 * In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
 * <p>
 * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
 * <p>
 * Example 1:
 * Input: nums = [3, 4, 2]
 * Output: 6
 * Explanation:
 * Delete 4 to earn 4 points, consequently 3 is also deleted.
 * Then, delete 2 to earn 2 points. 6 total points are earned.
 * Example 2:
 * Input: nums = [2, 2, 3, 3, 3, 4]
 * Output: 9
 * Explanation:
 * Delete 3 to earn 3 points, deleting both 2's and the 4.
 * Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
 * 9 total points are earned.
 * Note:
 * <p>
 * The length of nums is at most 20000.
 * Each element nums[i] is an integer in the range [1, 10000].
 */
public class _740DeleteAndEarn {
    public static void main(String[] args) {
        _740DeleteAndEarn test = new _740DeleteAndEarn();
        int[] nums = new int[]{2, 2, 3, 3, 3, 4};
        System.out.println(test.deleteAndEarn(nums));
    }

    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            if (Math.abs(nums[0] - nums[1]) <= 1) {
                return Math.max(nums[0], nums[1]);
            } else {
                return nums[0] + nums[1];
            }
        }

        int max = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        int[] count = new int[max + 1];
        for (int i = 0; i < n; i++) {
            count[nums[i]]++;
        }
        int[] dp = new int[max + 1];
        dp[1] = count[1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + count[i] * i, dp[i - 1]);
        }
        return dp[max];
    }

}
