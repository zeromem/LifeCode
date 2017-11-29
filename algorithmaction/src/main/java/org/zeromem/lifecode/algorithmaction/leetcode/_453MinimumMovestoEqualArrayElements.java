package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/11/29
 */
public class _453MinimumMovestoEqualArrayElements {
    public static void main(String[] args) {

    }
    public int minMoves(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += nums[i] - min;
        }
        return res;
    }
}
