package org.zeromem.lifecode.algorithmaction.july;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by zeromem on 2018/1/4.
 * hello world good bye  -> bye good world hello
 */
public class StringSplitReverse {
    public static void main(String[] args) {
        StringSplitReverse test = new StringSplitReverse();
        char[] s = " hello world goodbye  world.  _ ".toCharArray();
        System.out.println(test.splitReverse2(s));
    }

    public String splitReverse2(char[] s) {
        reverse(s, 0, s.length - 1);
        System.out.println(s);
        Deque<Character> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                int size = deque.size();
                for (int j = 0; j < size; j++) {
                    sb.append(deque.removeLast());
                }
                sb.append(' ');
            } else {
                deque.addLast(s[i]);
            }
        }
        for (int j = 0; j < deque.size(); j++) {
            sb.append(deque.pop());
        }
        return sb.toString();
    }

    public String splitReverse(char[] s) {
        reverse(s, 0, s.length - 1);

        int l = 0, h = 0;
        while (l < s.length) {
            if (s[l] == ' ') {
                l++;
                h++;
            } else if (h == s.length || s[h] == ' ') {
                reverse(s, l, h - 1);
                h++;
                l = h;
            } else {
                h++;
            }
        }
        return String.valueOf(s);
    }

    public static void reverse(char[] s, int l, int h) {
        while (l < h) {
            char tmp = s[l];
            s[l++] = s[h];
            s[h--] = tmp;
        }
    }
}
