package org.zeromem.lifecode.algorithmaction.july;

/**
 * Created by zeromem on 2018/1/17.
 */
public class StringContain {
    public static void main(String[] args) {
        StringContain test = new StringContain();
        String a = "hello world";
        String b = "hole";
        System.out.println(test.stringContain(a, b));

    }

    public boolean stringContain(String a, String b) {
        int ha = 0, hb = 0;
        char[] as = a.toCharArray();
        for (int i = 0; i < as.length; i++) {
            ha |= 1 << (as[i] - 'a');
        }
        char[] bs = b.toCharArray();
        for (int i = 0; i < bs.length; i++) {
            if ((ha & 1 << (bs[i] - 'a')) == 0) {
                return false;
            }
        }
        return true;
    }
}
