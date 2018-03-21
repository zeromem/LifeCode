package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/11/8
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.
 * <p>
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * <p>
 * Note: You may assume that n is not less than 2 and not larger than 58.
 */
public class _343IntegerBreakMultiplication {
    public static void main(String[] args) {
        _343IntegerBreakMultiplication test = new _343IntegerBreakMultiplication();
        System.out.println(test.maxProduct(4));
    }

    public int maxProduct(int n) {
        if (n <= 2) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            if (i - 1 > dp[i - 1]) {
                dp[i] = i - 1;
            } else {
                dp[i] = dp[i - 1];
            }
            for (int j = 1; j < i; j++) {
                if (dp[j] * (i - j) > dp[i]) {
                    dp[i] = dp[j] * (i - j);
                }
                if (j * (i - j) > dp[i]) {
                    dp[i] = j * (i - j);
                }
            }
        }
        return dp[n];
    }
}
