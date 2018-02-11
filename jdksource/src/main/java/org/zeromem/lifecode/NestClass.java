package org.zeromem.lifecode;

/**
 * @author zeromem
 * @date 2018/2/8
 */
public class NestClass {
    private static final String CONSTANT = "hello world";
    private String s;

    public NestClass(String _s) {
        s = _s;
    }

    public void print() {
        System.out.println(s);
    }

    class InnerClass {
        public InnerClass() {
            System.out.println("init inner class == " + CONSTANT);
            s = "inner";
        }

        public void print() {
            System.out.println(s);
        }
    }

    static class StaticInnerClass {
        public String sic;
        public StaticInnerClass() {
//            静态内部类不能访问外部类的非静态域
//            s = "static inner";
//            可以访问外部类的静态域
            System.out.println("init static init class == " + CONSTANT);
            sic = "static inner.";
        }
        public void print() {
            System.out.println(sic);
        }
    }

    public static void main(String[] args) {
        NestClass nest = new NestClass("nest");
        nest.print();

        InnerClass inner = nest.new InnerClass();
        nest.print();
        inner.print();

        StaticInnerClass sic = new NestClass.StaticInnerClass();
        sic.print();
    }
}
