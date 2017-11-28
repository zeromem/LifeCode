package org.zeromem.lifecode.algorithmaction.dynamicprogramming;

/**
 * @author zeromem
 * @date 2017/11/8
 * 最长公共子数组
 */
public class LongestCommonSubArray {
    public static void main(String[] args) {
        LongestCommonSubArray test = new LongestCommonSubArray();
        int[] a = new int[]{1, 2, 3, 2, 1};
        int[] b = new int[]{1, 2, 4, 3, 2, 1, 4, 7};
        System.out.println(test.commonSubarrLength(a, b));
    }

    public int commonSubarrLength(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j < n + 1; j++) {
            dp[0][j] = 0;
        }

        int max = 0;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        return max;
    }
}
