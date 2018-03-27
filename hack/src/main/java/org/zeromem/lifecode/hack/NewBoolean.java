package org.zeromem.lifecode.hack;

/**
 * @author zeromem
 * @date 2018/3/21
 */
public class NewBoolean {
    public static void main(String[] args) {
        Boolean b = Boolean.valueOf("true");
        Boolean b1 = Boolean.valueOf("true");
        Boolean b2 = b;
        b = false;
        System.out.println(b1);
        System.out.println(b2);
    }
}
