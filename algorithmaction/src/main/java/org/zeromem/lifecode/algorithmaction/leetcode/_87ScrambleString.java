package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeromem on 2018/3/8.
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * <p>
 * Below is one possible representation of s1 = "great":
 * <p>
 * great
 * /    \
 * gr    eat
 * / \    /  \
 * g   r  e   at
 * / \
 * a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * <p>
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 * <p>
 * rgeat
 * /    \
 * rg    eat
 * / \    /  \
 * r   g  e   at
 * / \
 * a   t
 * We say that "rgeat" is a scrambled string of "great".
 * <p>
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 * <p>
 * rgtae
 * /    \
 * rg    tae
 * / \    /  \
 * r   g  ta  e
 * / \
 * t   a
 * We say that "rgtae" is a scrambled string of "great".
 * <p>
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */
public class _87ScrambleString {
    public static void main(String[] args) {
        _87ScrambleString test = new _87ScrambleString();
        String s1 = "ejsejxhmgobegp";
        String s2 = "ggjeohbpejemsx";
        boolean result = test.isScramble(s1, s2);
        System.out.println(result);

    }

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        return isomorph(cs1, 0, cs1.length - 1, cs2, 0, cs2.length - 1);

    }

    public static boolean isomorph(char[] s1, int l1, int h1, char[] s2, int l2, int h2) {
        if (h1 - l1 != h2 - l2) {
            return false;
        }
        if (charsEqual(s1, l1, h1, s2, l2, h2)) {
            return true;
        }
        int[] count = new int[26];
        for (int i = l1; i <= h1; i++) {
            count[s1[i] - 'a']++;
        }
        for (int i = l2; i <= h2; i++) {
            count[s2[i] - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        for (int p = 1; p <= h1 - l1; p++) {
            if ((isomorph(s1, l1, l1 + p - 1, s2, l2, l2 + p - 1) && isomorph(s1, l1 + p, h1, s2, l2 + p, h2))
                    || (isomorph(s1, l1, l1 + p - 1, s2, h2 - p + 1, h2) && isomorph(s1, l1 + p, h1, s2, l2, h2 - p))) {
                return true;
            }
        }
        return false;
    }

    public static boolean charsEqual(char[] s1, int l1, int h1, char[] s2, int l2, int h2) {
        for (int i = l1, j = l2; i <= h1; i++, j++) {
            if (s1[i] != s2[j]) {
                return false;
            }
        }
        return true;
    }

}
