package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zeromem
 * @date 2018/3/29
 * Given an encoded string, return it's decoded string.
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * <p>
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * <p>
 * Examples:
 * <p>
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class _394DecodeString {
    public static void main(String[] args) {
        _394DecodeString test = new _394DecodeString();
        String s = "3[a2[c]]";
        System.out.println(test.decodeString(s));
    }

    public String decodeString(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return "";
        }
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == ']') {
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty()) {
                    char sc = stack.pop();
                    if (sc == '[') {
                        break;
                    }
                    sb.append(sc);
                }
                String ele = sb.reverse().toString();

                StringBuilder nsb = new StringBuilder();
                while (!stack.isEmpty()) {
                    char nc = stack.pop();
                    if (nc > '9' || nc < '0') {
                        stack.push(nc);
                        break;
                    }
                    nsb.append(nc);
                }
                int k = Integer.valueOf(nsb.reverse().toString());

                for (int i = 0; i < k - 1; i++) {
                    sb.append(ele);
                }
                for (int i = 0; i < sb.length(); i++) {
                    stack.push(sb.charAt(i));
                }
            } else {
                stack.push(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }
}
