package org.zeromem.lifecode.algorithmaction.dynamicprogramming;

/**
 * @author zeromem
 * @date 2017/11/3
 */
public class NumberTower {
    public static void main(String[] args) {
        NumberTower test = new NumberTower();
        int[][] nums = new int[][]{
                {9},
                {12, 15},
                {10, 6, 8},
                {2, 18, 9, 5},
                {19, 7, 10, 4, 16}
        };
        System.out.println(test.maxPathSum(nums));
    }

    public int maxPathSum(int[][] tower) {
        int n = tower.length;
        int[][] dp = new int[n][n];
        dp[0][0] = tower[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = tower[i][0] + dp[i - 1][0];
            dp[i][i] = tower[i][i] + dp[i - 1][i - 1];
        }

        for (int j = 1; j < n - 1; j++) {
            for (int i = 2; i < n; i++) {
                if (dp[j - 1][i - 1] > dp[j][i - 1]) {
                    dp[j][i] += dp[j - 1][i - 1];
                } else {
                    dp[j][i] += dp[j][i - 1];
                }
            }
        }
        int max = 0;
        for (int j = 0; j < n; j++) {
            if (dp[j][n - 1] > max) {
                max = dp[j][n - 1];
            }
        }

        return max;
    }
}
