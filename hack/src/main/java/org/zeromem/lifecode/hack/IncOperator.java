package org.zeromem.lifecode.hack;

/**
 * @author zeromem
 * @date 2017/10/19
 */
public class IncOperator {
    public static void main(String[] args) {
        int i = 0;
        int x = i+++i++-++i+i++;
        System.out.println(x);
        System.out.println(i);

    }
}
