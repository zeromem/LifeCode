package org.zeromem.lifecode.algorithmaction.dynamicprogramming;

/**
 * @author zeromem
 * @date 2017/11/29
 * <p>
 * Given a string s and a string t, check if s is subsequence of t.
 * <p>
 * You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
 * <p>
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 * <p>
 * Example 1:
 * s = "abc", t = "ahbgdc"
 * <p>
 * Return true.
 * <p>
 * Example 2:
 * s = "axc", t = "ahbgdc"
 * <p>
 * Return false.
 * <p>
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
 * <p>
 * Credits:
 * Special thanks to @pbrother for adding this problem and creating all test cases.
 */
public class _392IsSubsequence {
    public static void main(String[] args) {
        _392IsSubsequence test = new _392IsSubsequence();
        String s = "";
        String t = "ahbgdc";
        System.out.println(test.isSubsequence(s, t));

    }

    public boolean isSubsequence(String s, String t) {
        if (t == null && s == null) {
            return true;
        }
        if (t == null) {
            return false;
        }
        if (s == null) {
            return true;
        }

        int m = s.length();
        int n = t.length();
        int i = 0, j = 0;
        if (j == m) {
            return true;
        }
        for (; i < n; i++) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
                if (j == m) {
                    return true;
                }
            }
        }

        return false;
    }
}
