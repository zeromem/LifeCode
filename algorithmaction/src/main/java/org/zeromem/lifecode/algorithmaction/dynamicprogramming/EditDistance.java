package org.zeromem.lifecode.algorithmaction.dynamicprogramming;

/**
 * @author zeromem
 * @date 2017/11/6
 * 编辑距离
 */
public class EditDistance {
    public static void main(String[] args) {
        EditDistance test = new EditDistance();
        char[] a = "accbae".toCharArray();
        char[] b = "abcd".toCharArray();
        System.out.println(test.editDistance(a, b));
    }

    public int editDistance(char[] a, char[] b) {
        int m = a.length;
        int n = b.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (dp[i - 1][j] < dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1] + 1;
                }
            }
        }
        // 最优路径还原
        return dp[m][n];
    }
}
