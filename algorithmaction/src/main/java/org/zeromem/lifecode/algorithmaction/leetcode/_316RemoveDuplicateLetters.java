package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zeromem
 * @date 2017/11/8
 */
public class _316RemoveDuplicateLetters {
    public static void main(String[] args) {
        _316RemoveDuplicateLetters test = new _316RemoveDuplicateLetters();
        System.out.println(test.remove("cbacdcbc"));
    }

    public String remove(String s) {
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer>[] indexes = new LinkedList[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (indexes[s.charAt(i) - 'a'] == null) {
                indexes[s.charAt(i) - 'a'] = new LinkedList<>();
            }
            indexes[s.charAt(i) - 'a'].addLast(i);
        }

        int i = 0;
        while (i < 26) {
            System.out.println(Arrays.toString(indexes));
            for (i = 0; i < 26; i++) {
                if (indexes[i] != null) {
                    int first = indexes[i].getFirst();
                    boolean flag = true;
                    for (int j = i + 1; j < 26; j++) {
                        if (indexes[j] != null && first > indexes[j].getLast()) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        sb.append((char)('a' + i));
                        indexes[i] = null;
                        for (int j = i + 1; j < 26; j++) {
                            if (indexes[j] != null) {
                                while (!indexes[j].isEmpty() && first > indexes[j].getFirst()) {
                                    indexes[j].removeFirst();
                                }
                            }
                        }
                        System.out.println(i);
                        indexes[i] = null;
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }
}
