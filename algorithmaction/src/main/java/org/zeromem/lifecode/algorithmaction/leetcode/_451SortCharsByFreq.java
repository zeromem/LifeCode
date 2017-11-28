package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.LinkedList;
import java.util.TreeMap;

/**
 * Created by zeromem on 2016/11/7.
 */
public class _451SortCharsByFreq {
    public static void main(String[] args) {
        _451SortCharsByFreq test = new _451SortCharsByFreq();
        System.out.println(test.frequencySort("中国hello world"));
    }

    public String frequencySort(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        char[] cs = s.toCharArray();
        int[] cnt = new int[256];
        for (char c : cs) {
            cnt[c]++;
        }

        TreeMap<Integer, LinkedList<Character>> map = new TreeMap<Integer, LinkedList<Character>>();
        for (int i = 0; i < 256; i++) {
            if (cnt[i] == 0) continue;
            if (!map.containsKey(cnt[i])) {
                map.put(cnt[i], new LinkedList<Character>());
            }
            map.get(cnt[i]).add((char) i);
        }
        StringBuilder sb = new StringBuilder();
        for (Integer count : map.descendingKeySet()) {
            for (Character c : map.get(count)) {
                for (int i = 0; i < count; i++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
