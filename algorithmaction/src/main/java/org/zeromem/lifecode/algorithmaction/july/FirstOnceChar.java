package org.zeromem.lifecode.algorithmaction.july;

/**
 * Created by zeromem on 2018/1/18.
 * 第一个仅出现一次的字符
 */
public class FirstOnceChar {
    public static void main(String[] args) {
        FirstOnceChar test = new FirstOnceChar();
        String s = "abaccffb";
        System.out.println(test.firstOnceChar(s));
    }

    public char firstOnceChar(String s) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            count[c - 'a']++;
        }

        for (char c : chars) {
            if (count[c - 'a'] == 1) {
                return c;
            }
        }
        return '*';
    }
}
