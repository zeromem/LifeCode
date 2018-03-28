package org.zeromem.lifecode.hack;

/**
 * @author zeromem
 * @date 2018/2/22
 */
public class TryCatchReturn<V> {
    public static void main(String[] args) {
        TryCatchReturn<String> test = new TryCatchReturn<>();
        String retV = test.retV("hello world");
        System.out.println(retV);
    }

    public V retV(V i) {
        // 必须用while(true)包围方法体，因为catch没有返回值，方法只能从try中产生返回值
        while (true) {
            try {
                return produceV(i);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    public V produceV(V v) throws IllegalArgumentException {
        return v;
    }
}
