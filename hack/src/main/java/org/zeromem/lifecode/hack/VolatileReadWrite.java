package org.zeromem.lifecode.hack;

/**
 * @author zeromem
 * @date 2018/3/21
 */
public class VolatileReadWrite {
    private static volatile int a = 1;
    public static void main(String[] args) {
        a = 2;
    }
}
