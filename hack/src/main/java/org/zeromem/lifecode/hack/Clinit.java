package org.zeromem.lifecode.hack;

/**
 * @author zeromem
 * @date 2017/10/25
 */
public class Clinit {
    static int a = 1;
    static {
        b = 5;
    }
    static int b = 6;

    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        ClassLoader ext = systemClassLoader.getParent();
        System.out.println(ext);
        ClassLoader parent = ext.getParent();
        System.out.println(parent);

        // will output 6.
        System.out.println(b);
        System.out.println(a);
    }
}
