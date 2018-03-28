package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/19
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example:
 * <p>
 * Input: "babad"
 * <p>
 * Output: "bab"
 * <p>
 * Note: "aba" is also a valid answer.
 * <p>
 * <p>
 * Example:
 * <p>
 * Input: "cbbd"
 * <p>
 * Output: "bb"
 */
public class _5LongestPalindromicSubstring {
    public static void main(String[] args) {

    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        char[] cs = s.toCharArray();
        int n = cs.length;
        // dp[i][j] s[i,j]是否为回文
        boolean[][] dp = new boolean[n][n];

        int max = 1, l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = cs[i] == cs[i + 1];
            if (dp[i][i + 1]) {
                max = 2;
                l = i;
                r = i + 1;
            }
        }

        for (int delta = 2; delta < n; delta++) {
            for (int i = 0; i + delta < n; i++) {
                dp[i][i + delta] = dp[i + 1][i + delta - 1] && cs[i] == cs[i + delta];
                if (dp[i][i + delta]) {
                    max = delta;
                    l = i;
                    r = i + delta;
                }
            }
        }
        return new String(cs, l, r - l + 1);
    }
}
