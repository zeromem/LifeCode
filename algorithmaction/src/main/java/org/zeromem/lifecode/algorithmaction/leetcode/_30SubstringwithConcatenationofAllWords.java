package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.*;

/**
 * @author zeromem
 * @date 2018/3/7
 * You are given a string, s,
 * and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s
 * that is a concatenation of each word in words exactly once and without any intervening characters.
 * <p>
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * <p>
 * You should return the indices: [0,9].
 * (order does not matter).
 */
public class _30SubstringwithConcatenationofAllWords {
    public static void main(String[] args) {
        _30SubstringwithConcatenationofAllWords test = new _30SubstringwithConcatenationofAllWords();
        String s = "wordgoodgoodgoodbestword";
        String[] words = new String[]{"word", "good", "best", "good"};
        List<Integer> result = test.findSubstring(s, words);
        System.out.println(result);
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new LinkedList<>();
        if (words == null || words.length == 0) {
            return result;
        }
        int wordsLength = words.length * words[0].length();
        if (wordsLength > s.length()) {
            return result;
        }
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            Integer count = wordCount.putIfAbsent(word, 1);
            if (count != null) {
                wordCount.put(word, count + 1);
            }
        }

        int endPos = s.length() - wordsLength;
        for (int i = 0; i <= endPos; i++) {
            Map<String, Integer> copy = new HashMap<>(wordCount);
            if (match(s, i, copy)) {
                result.add(i);
            }
        }
        return result;

    }

    public static boolean match(String s, int i, Map<String, Integer> wordCount) {
        for (String word : wordCount.keySet()) {
            if (s.startsWith(word, i)) {
                int count = wordCount.get(word);
                if (count == 1) {
                    wordCount.remove(word);
                } else {
                    wordCount.put(word, count - 1);
                }
                if (wordCount.isEmpty()) {
                    return true;
                } else {
                    return match(s, i + word.length(), wordCount);
                }
            }
        }
        return false;
    }
}
