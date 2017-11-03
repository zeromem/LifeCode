package org.zeromem.lifecode.algorithmaction.dynamicprogramming;

/**
 * @author zeromem
 * @date 2017/11/3
 * 最大连续子数组之和
 */
public class MaxSubarraySum {
    public static void main(String[] args) {
        MaxSubarraySum test = new MaxSubarraySum();
        System.out.println(test.maxSubSum(new int[]{-2, 11, -4, 13, -5, -2}));
    }

    public int maxSubSum(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0] < 0 ? 0 : nums[0];
        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    /**
     * @author zeromem
     * @date 2017/11/2
     * 最长回文子序列，区别于最长回文子串
     * 通过删除字符串中的某些字符，使得剩余的子序列形成回文
     */
    public static class _5_1LongestPalindromicSubsequence {
        public static void main(String[] args) {
            _5_1LongestPalindromicSubsequence test = new _5_1LongestPalindromicSubsequence();
            System.out.println(test.longestPalindromeSubsequence("abaasadhafha"));

        }

        public int longestPalindromeSubsequence(String s) {
            int n = s.length();
            int[][] dp = new int[n][n];

            for (int i = 0; i < n; i++) {
                dp[i][i] = 1;
            }
            for (int i = 0; i < n - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = 2;
                } else {
                    dp[i][i + 1] = 1;
                }
            }

            for (int delta = 2; delta < n; delta++) {
                for (int i = 0; i + delta < n; i++) {
                    if (s.charAt(i) == s.charAt(i + delta)) {
                        dp[i][i + delta] = dp[i + 1][i + delta - 1] + 2;
                    } else if (dp[i + 1][i + delta] > dp[i][i + delta - 1]) {
                        dp[i][i + delta] = dp[i + 1][i + delta];
                    } else {
                        dp[i][i + delta] = dp[i][i + delta - 1];
                    }
                }
            }

            return dp[0][n - 1];
        }
    }

    /**
     * @author zeromem
     * @date 2017/11/2
     * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

    Example:

    Input: "babad"

    Output: "bab"

    Note: "aba" is also a valid answer.
    Example:

    Input: "cbbd"

    Output: "bb"
     */
    public static class _5LongestPalindromicSubstr {
        public static void main(String[] args) {
            _5LongestPalindromicSubstr test = new _5LongestPalindromicSubstr();
            System.out.println(test.longestPalindrome("abcba"));
        }

        /**
         * 最长回文子串，区别于最长回文子序列
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {
            int n = s.length(), left = 0, right = 0, max = 1;
    //        子串[i...j]是否是回文
            boolean[][] dp = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                dp[i][i] = true;
            }
            for (int i = 0; i < n - 1; i++) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
                if (dp[i][i + 1]) {
                    max = 2;
                    left = i;
                    right = i + 1;
                }
            }

            for (int delta = 2; delta < n; delta++) {
                for (int i = 0; i + delta < n; i++) {
                    dp[i][i + delta] = dp[i + 1][i + delta - 1] && s.charAt(i) == s.charAt(i + delta);
                    if (dp[i][i + delta] && delta + 1 > max) {
                        max = delta + 1;
                        left = i;
                        right = i + delta;
                    }
                }
            }
            return s.substring(left, right + 1);
        }
    }
}
