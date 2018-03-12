package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/12
 */
public class _674LongestContinuousIncreasingSubsequence {
    public static void main(String[] args) {
        _674LongestContinuousIncreasingSubsequence test = new _674LongestContinuousIncreasingSubsequence();
        int[] nums = new int[]{1, 3, 5, 7};
        int result = test.findLengthOfLCIS(nums);
        System.out.println(result);

    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        int max = 1;
        int cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                cnt++;
            } else {
                if (cnt > max) {
                    max = cnt;
                }
                cnt = 1;
            }
        }
        if (cnt > max) {
            return cnt;
        }
        return max;
    }
}
