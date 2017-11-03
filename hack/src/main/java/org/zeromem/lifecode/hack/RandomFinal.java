package org.zeromem.lifecode.hack;

import java.util.Objects;

/**
 * @author zeromem
 * @date 2017/10/25
 */
public class RandomFinal {
    public RandomFinal() {

    }

    public RandomFinal(String x) {
        this();
        System.out.println("INIT");
    }

    /**
     * a 将被放到<clinit>方法中初始化
     */
    public static final double a = Math.random();
    /**
     * b是一个常量，不会出现在<clinit>方法中
     */
    public static final double b = 1;

    String s = "hello";
    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(b);
        int[] x = new int[10];
        System.out.println(x instanceof Object);
        System.out.println(Objects.hashCode(null));
    }
}
