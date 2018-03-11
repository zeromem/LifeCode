package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/9
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * <p>
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * <p>
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * Note:
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class _494TargetSum {
    public static void main(String[] args) {
        _494TargetSum test = new _494TargetSum();
        int[] nums = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1};
        int S = 1;
        System.out.println(test.findTargetSumWays(nums, S));
    }

    public int findTargetSumWays(int[] nums, int S) {
        return f(nums, nums.length, S);
    }

    public static int f(int[] nums, int i, int s) {
        if (i == 1) {
            if (nums[0] == 0 && s == 0) {
                return 2;
            }
            if (nums[0] == s) {
                return 1;
            }
            if (nums[0] == -s) {
                return 1;
            }
            return 0;
        }
        return f(nums, i - 1, s + nums[i - 1]) + f(nums, i - 1, s - nums[i - 1]);
    }
}
