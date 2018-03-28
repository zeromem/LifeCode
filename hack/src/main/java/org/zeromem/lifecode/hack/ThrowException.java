package org.zeromem.lifecode.hack;

/**
 * @author zeromem
 * @date 2018/1/18
 */
public class ThrowException {
    public static void main(String[] args) {
        ThrowException test = new ThrowException();
        test.outer();
    }

    public void innerEx() {
        try {
            throwEx();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void outer() {
        System.out.println("outer start.");
        try {
            innerEx();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("hello world");
            System.out.println(e);
        }
        System.out.println("outer over.");
    }

    public void throwEx() throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException("out.");
    }
}
