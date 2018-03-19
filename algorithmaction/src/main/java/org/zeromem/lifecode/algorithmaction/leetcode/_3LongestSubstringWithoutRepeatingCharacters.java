package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zeromem
 * @date 2018/3/19
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class _3LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        _3LongestSubstringWithoutRepeatingCharacters test = new _3LongestSubstringWithoutRepeatingCharacters();
        String s = "pwwkew";
        System.out.println(test.lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() <= 1) {
            return s.length();
        }

        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int start1 = map.getOrDefault(s.charAt(i), -1);
            start1++;
            if (start1 > start) {
                start = start1;
            }
            if (i - start + 1 > result) {
                result = i - start + 1;
            }
            map.put(s.charAt(i), i);
        }
        return result;
    }
}
