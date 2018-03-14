package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/13
 */
public class _796RotateString {
    public static void main(String[] args) {

    }
    public boolean rotateString(String A, String B) {
        if (A == null && B == null) {
            return true;
        }
        if (A == null || B == null) {
            return false;
        }
        if (A.length() != B.length()) {
            return false;
        }
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        for (int i = 0; i < a.length; i++) {
            boolean flag = true;
            int k = i;
            for (char aB : b) {
                if (aB != a[k]) {
                    flag = false;
                    break;
                } else {
                    k = (k + 1) % a.length;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }
}
