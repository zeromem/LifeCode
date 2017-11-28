package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/11/8
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */
public class _55ArrayJumpGame {
    public static void main(String[] args) {
        _55ArrayJumpGame test = new _55ArrayJumpGame();
        int[] a = new int[]{2, 3, 1, 1, 4};
        int[] b = new int[]{3, 2, 1, 0, 4};
        int[] c = new int[]{2, 0};
        System.out.println(test.canJump(c));
    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (nums.length <= 1) {
            return true;
        }
        if (nums[0] <= 0) {
            return false;
        }
        int remainSteps = nums[0];
        for (int i = 1; i < n; i++) {
            remainSteps--;
            if (nums[i] > remainSteps) {
                remainSteps = nums[i];
            }
            if (remainSteps == 0) {
                return false;
            }
            if (i + remainSteps >= n - 1) {
                return true;
            }
        }
        boolean[] can = new boolean[nums.length];
        can[n - 1] = true;

        for (int i = n - 2; i >= 0; i--) {
            int j = i + nums[i];
            if (j > n - 1) {
                j = n - 1;
            }
            for (;j > i; j--) {
                if (can[j]) {
                    can[i] = true;
                    break;
                }
            }
        }

        return can[0];
    }
}
