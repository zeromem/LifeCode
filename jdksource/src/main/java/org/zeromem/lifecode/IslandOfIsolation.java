package org.zeromem.lifecode;

import java.util.Vector;

/**
 * @author zeromem
 * @date 2018/3/6
 */
public class IslandOfIsolation {
    public static void main(String[] args) throws InterruptedException {
        Vector v1 = new Vector(214744444);
        Vector v2 = new Vector(214744444);

        SomeOne s1 = new SomeOne();
        SomeOne s2 = new SomeOne();

        s1.other = s2;
        s2.other = s1;

        // java可以解决循环引用的问题，但是必须将s1 和s2都赋值为null，gc才会回收
        s1 = null;
        s2 = null;

        System.gc();

        Thread.sleep(2000);
    }
}

class SomeOne {
    SomeOne other;

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this + " over.");
    }
}
