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

        int endPos = s.length() - wordsLength;
        boolean[] wordVisited = new boolean[words.length];
        for (int i = 0; i <= endPos; i++) {
            if (match(s, i, words, Arrays.copyOf(wordVisited, wordVisited.length))) {
                result.add(i);
            }
        }
        return result;

    }

    public static boolean match(String s, int i, String[] words, boolean[] visited) {
        for (int j = 0; j < words.length; j++) {
            if (visited[j]) {
                continue;
            }
            String word = words[j];
            if (s.startsWith(word, i)) {
                visited[j] = true;
                if (allVisited(visited)) {
                    return true;
                } else {
                    return match(s, i + word.length(), words, visited);
                }
            }
        }
        return false;
    }

    public static boolean allVisited(boolean[] visited) {
        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
