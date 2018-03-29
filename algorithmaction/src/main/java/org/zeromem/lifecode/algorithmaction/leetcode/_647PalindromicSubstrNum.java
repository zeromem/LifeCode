package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/11/28
 * 字符串的回文子串数量
 * <p>
 * Given a string, your task is to count how many palindromic substrings in this string.
 * <p>
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 * <p>
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class _647PalindromicSubstrNum {
    public static void main(String[] args) {
        _647PalindromicSubstrNum test = new _647PalindromicSubstrNum();
        String s = "aaa";
        System.out.println(test.countSubstrings(s));
    }

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int result = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            // s[i] as the center char
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                result++;
                left--;
                right++;
            }

            // s[i] as the first left char
            left = i;
            right = i + 1;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                    result++;
                    left--;
                    right++;
            }
        }
        return result;
    }
}
