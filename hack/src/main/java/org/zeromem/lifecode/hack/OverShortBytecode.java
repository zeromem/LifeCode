package org.zeromem.lifecode.hack;

/**
 * @author zeromem
 * @date 2017/10/25
 */
public class OverShortBytecode {
    public static void main(String[] args) {
        // ldc .
        int a = 10000000;
        int b = 2;
        // istore_3
        int c = 3;
        // istore 4
        int d = 4;
        int e = 5;
        int f = 6;
        int g = 7;
        int h = 8;
        int i = 9;
    }
}
