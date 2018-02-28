package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zeromem
 * @date 2018/2/27
 */
public class _784LetterCasePermutation {
    public static void main(String[] args) {
        _784LetterCasePermutation test = new _784LetterCasePermutation();
        String s = "a1b2";
        List<String> res = test.letterCasePermutation(s);
        System.out.println(res);
    }

    public List<String> letterCasePermutation(String S) {
        List<String> res = backtrack(S);
        return res;
    }

    public static List<String> backtrack(String s) {
        List<String> partial = new ArrayList<>();
        int i;
        char c = 0;
        for (i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c > '9') {
                break;
            }
        }
        if (i == s.length()) {
            partial.add(s);
            return partial;
        }
        String head1 = s.substring(0, i) + c;
        String head2;
        if (c >= 'A' && c <= 'Z') {
            head2 = s.substring(0, i) + (char) (c + ('a' - 'A'));
        } else {
            head2 = s.substring(0, i) + (char) (c - ('a' - 'A'));
        }
        List<String> tails = backtrack(s.substring(i + 1));
        for (String tail : tails) {
            partial.add(head1 + tail);
            partial.add(head2 + tail);
        }
        return partial;
    }
}
