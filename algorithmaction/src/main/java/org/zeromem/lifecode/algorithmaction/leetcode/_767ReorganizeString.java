package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zeromem
 * @date 2018/3/20
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 * <p>
 * If possible, output any possible result.  If not possible, return the empty string.
 * <p>
 * Example 1:
 * <p>
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 * <p>
 * Input: S = "aaab"
 * Output: ""
 */
public class _767ReorganizeString {
    public static void main(String[] args) {

    }

    public String reorganizeString(String S) {
        // TODO: 2018/3/20
        if (S == null || S.length() <= 1) {
            return S;
        }
        int[] count = new int[26];
        for (char c : S.toCharArray()) {
            count[c - 'a']++;
        }
        PriorityQueue<Character> queue = new PriorityQueue<>(26, Comparator.reverseOrder());
        return null;
    }


}
