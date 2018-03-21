package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/12/1
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * <p>
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * <p>
 * The number of ways decoding "12" is 2.
 */
public class _91DecodeWays {
    public static void main(String[] args) {
        _91DecodeWays test = new _91DecodeWays();
        String s = "17";
        System.out.println(test.numDecodings(s));
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = canDecode(s.charAt(0)) ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            dp[i] += canDecode(s.charAt(i - 1)) ? dp[i - 1] : 0;
            dp[i] += canDecode(s.charAt(i - 2), s.charAt(i - 1)) ? dp[i - 2] : 0;
        }
        return dp[n];
    }

    private boolean canDecode(char a) {
        return a != '0';
    }

    private boolean canDecode(char a, char b) {
        return a == '1' || (a == '2' && b <= '6');
    }
}
