package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2018/3/20
 * 从位置0跳到最后一个位置，所需的最小步数
 * dp[i]表示从0跳到i的最小步数。
 * 对于nums[i]和dp[i],从位置i起跳，最远能到达i + nums[i],且步数为dp[i] + 1
 * 假设总能从0跳到最后
 */
public class _45JumpGameII {
    public static void main(String[] args) {
        _45JumpGameII test = new _45JumpGameII();
        int[] nums = new int[]{3,2,1,1,4};
        System.out.println(test.jump(nums));
    }

    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int n = nums.length;
        int result = 0;
        // 上次能达到的最远位置
        int last = 0;
        // 当前能达到的最远位置
        int cur = 0;
        for (int i = 0; i < n; i++) {
            if (i > last) {
                last = cur;
                result++;
                if (cur >= n - 1) {
                    break;
                }
            }
            cur = Math.max(cur, i + nums[i]);
        }
        return result;
    }

    public int jump1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int[] dp = new int[nums.length];
        boolean[] visited = new boolean[nums.length];
        visited[0] = true;
        for (int i = 0; i < nums.length; i++) {
            int step = nums[i];
            for (int j = 1; j <= step && i + j < nums.length; j++) {
                if (!visited[i + j]) {
                    visited[i + j] = true;
                    dp[i + j] = dp[i] + 1;
                    if (i + j == nums.length - 1) {
                        return dp[i + j];
                    }
                }
            }
        }
        return dp[nums.length - 1];
    }
}
