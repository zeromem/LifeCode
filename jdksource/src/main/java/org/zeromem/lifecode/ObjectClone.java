package org.zeromem.lifecode;

/**
 * @author zeromem
 * @date 2018/3/8
 */
public class ObjectClone implements Cloneable {
    String a = "hello world";
    public static void main(String[] args) throws CloneNotSupportedException {
        ObjectClone test = new ObjectClone();
        ObjectClone clone = (ObjectClone) test.clone();
        System.out.println(clone.a == test.a);
        System.out.println(clone.a);
        System.out.println(test.a);
    }
}
