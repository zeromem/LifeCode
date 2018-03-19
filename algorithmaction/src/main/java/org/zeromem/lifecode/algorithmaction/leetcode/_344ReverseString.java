package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/16
 */
public class _344ReverseString {
    public static void main(String[] args) {
        _344ReverseString test = new _344ReverseString();
        String s = "helloworld";
        System.out.println(test.reverseString(s));

    }

    public String reverseString(String s) {
        char[] cs = s.toCharArray();
        int num = cs.length / 2;
        for (int i = 0; i < num; i++) {
            swap(cs, i, cs.length - 1- i);
        }
        return String.valueOf(cs);

    }

    public static void swap(char[] cs, int i, int j) {
        char tmp = cs[i];
        cs[i] = cs[j];
        cs[j] = tmp;
    }
}
