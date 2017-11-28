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
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = tower[n - 1][j];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if (dp[i + 1][j] > dp[i + 1][j + 1]) {
                    dp[i][j] = tower[i][j] + dp[i + 1][j];
                } else {
                    dp[i][j] = tower[i][j] + dp[i + 1][j + 1];
                }
            }
        }

        return dp[0][0];
    }
}
