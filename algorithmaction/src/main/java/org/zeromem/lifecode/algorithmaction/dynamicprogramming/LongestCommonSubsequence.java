package org.zeromem.lifecode.algorithmaction.dynamicprogramming;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zeromem
 * @date 2017/11/6
 * 最长公共子序列
 * 一个序列 S ，如果分别是两个或多个已知序列的子序列，且是所有符合此条件序列中最长的，则 S 称为已知序列的最长公共子序列。
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        LongestCommonSubsequence test = new LongestCommonSubsequence();
        char[] a = "agcgcaatg".toCharArray();
        char[] b = "gccctagcg".toCharArray();
        System.out.println(test.lcsLength(a, b));

    }

    /**
     * a和b的最长公共子序列长度
     *
     * @param a
     * @param b
     * @return
     */
    public List<Character> lcsLength(char[] a, char[] b) {
        int m = a.length;
        int n = b.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else if (dp[i - 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        // 最优路径还原
        LinkedList<Character> deque = new LinkedList<>();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (a[i - 1] == b[j - 1]) {
                deque.addFirst(a[i - 1]);
                i--;
                j--;
            } else if (dp[i - 1][j] == dp[i][j]) {
                // 保持和前面相同的优先比较顺序
                i--;
            } else {
                j--;
            }
        }
        return deque;
    }
}
