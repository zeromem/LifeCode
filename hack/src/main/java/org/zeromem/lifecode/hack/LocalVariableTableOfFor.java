package org.zeromem.lifecode.hack;

/**
 * @author zeromem
 * @date 2017/10/26
 */
public class LocalVariableTableOfFor {
    public static void main(String[] args) {
        Number a = 000000001d;
        System.out.println(a);
        System.out.println(a.getClass());

        for (int i = 0; i < 100; i++) {
            // class的局部变量表中会有args和i，不会有xxxx
            int xxxx = i;
        }
    }
}
