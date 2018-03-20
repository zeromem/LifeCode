package org.zeromem.lifecode.algorithmaction.dynamicprogramming;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2017/11/6
 * 0-1背包问题
 * N个宝石，序号为i = [0, 1,..., N - 1]
 * 体积为v[i],重量为w[i]
 * 背包体积为C,最背包能放下的最大重
 */
public class Knapsack {
    public static void main(String[] args) {
        Knapsack test = new Knapsack();
        int capacity = 8;
        int[] volume = new int[]{4, 3, 5, 2, 5};
        int[] weight = new int[]{9, 6, 1, 4, 1};
        System.out.println(test.knapsack(capacity, volume, weight));

        capacity = 9;
        volume = new int[]{4, 3, 4, 2};
        weight = new int[]{20, 6, 20, 4};
        System.out.println(test.knapsack(capacity, volume, weight));

    }

    public int knapsack(int capacity, int[] volume, int[] weight) {
        int n = volume.length;
        // dp[i][j]: 前i个宝石，剩余容量为j时的最优选择
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                if (j >= volume[i - 1]) {
                    // 选第i个宝石
                    dp[i][j] = Math.max(dp[i - 1][j - volume[i - 1]] + weight[i - 1], dp[i - 1][j]);
                } else {
                    // 不选第i个宝石
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 宝石选择路径
        boolean[] selected = new boolean[n];
        int c = capacity;
        for (int i = n; i > 0; i--) {
            // 若dp[i][c] == dp[i - 1][c]，则一定是没有选择宝石i
            // 否则，一定是dp[i][c] > dp[i - 1][c]，且选择了宝石i
            if (dp[i][c] > dp[i - 1][c]) {
                selected[i - 1] = true;
                c -= volume[i - 1];
            }
        }
        // System.out.println(Arrays.toString(selected));
        return dp[n][capacity];
    }
}
