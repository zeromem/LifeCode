package org.zeromem.lifecode.algorithmaction.july;

/**
 * Created by zeromem on 2018/1/4.
 * 字符串循环移位
 */
public class StringRotate {
    public static void main(String[] args) {
        StringRotate test = new StringRotate();
        String s = "jhalskdfh";
        System.out.println(test.rotate(s, 3));
        System.out.println(test.rotate(s, 13));
    }

    public String rotate(String s, int k) {
        k %= s.length();
        return new StringBuilder(s.substring(0, k)).reverse().append(
                new StringBuilder(s.substring(k)).reverse()
        ).reverse().toString();
    }

}
