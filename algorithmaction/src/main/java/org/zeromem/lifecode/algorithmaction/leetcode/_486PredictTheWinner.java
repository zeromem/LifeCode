package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2017/11/28
 * 一个数组，两个人轮流取数，每次只能从首尾取，预测先取的人是否会赢（平分也算）
 * dp[i][j]表示在nums[i,...,j]区间内，先取的人比后取的人多出的分数。
 * dp[i][j] = max{nums[i] - dp[i+1][j], nums[j] - dp[i][j-1]}
 * 最终取dp[0][n-1]是否大于等于0
 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.
 * <p>
 * Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.
 * <p>
 * Example 1:
 * Input: [1, 5, 2]
 * Output: False
 * Explanation: Initially, player 1 can choose between 1 and 2.
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 * Hence, player 1 will never be the winner and you need to return False.
 * Example 2:
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 * Note:
 * 1 <= length of the array <= 20.
 * Any scores in the given array are non-negative integers and will not exceed 10,000,000.
 * If the scores of both players are equal, then player 1 is still the winner.
 */
public class _486PredictTheWinner {
    public static void main(String[] args) {
        _486PredictTheWinner test = new _486PredictTheWinner();
        int[] nums = new int[]{1, 5, 2};

        System.out.println(test.PredictTheWinner(nums));
    }

    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return true;
        }

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        for (int gap = 1; gap < n; gap++) {
            for (int i = 0; i + gap < n; i++) {
                int j = i + gap;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        System.out.println(Arrays.deepToString(dp));

        return dp[0][n - 1] >= 0;
    }
}
