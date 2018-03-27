package org.zeromem.lifecode.hack;

/**
 * @author zeromem@qq.com
 * @date 2018/3/20
 */
public class VolatileReadWrite {
    private static volatile Integer a = 1;
    public static void main(String[] args) {
        a = 2;
    }
}
