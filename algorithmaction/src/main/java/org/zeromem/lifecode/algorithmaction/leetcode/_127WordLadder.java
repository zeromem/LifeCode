package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.*;

/**
 * @author zeromem
 * @date 2018/3/1
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * For example,
 * <p>
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * <p>
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 */
public class _127WordLadder {
    public static void main(String[] args) {
        _127WordLadder test = new _127WordLadder();
        String begin = "hit";
        String end = "cog";
//        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "lot", "log");
        System.out.println(test.ladderLength(begin, end, wordList));

    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return step;
                }
                for (String nei : neighbors(word, dict)) {
                    queue.add(nei);
                    dict.remove(nei);
                }
            }
            step++;
        }
        return 0;
    }

    public static List<String> neighbors(String word, Set<String> dict) {
        List<String> result = new LinkedList<>();
        char[] cs = word.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char old = cs[i];
            for (char j = 'a'; j <= 'z'; j++) {
                cs[i] = j;
                String newWord = new String(cs);
                if (dict.contains(newWord)) {
                    result.add(newWord);
                }
            }
            cs[i] = old;
        }
        return result;
    }
}
