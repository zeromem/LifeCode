package org.zeromem.lifecode;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Condition;

/**
 * @author zeromem
 * @date 2018/2/11
 */
public class UseWeakHashMap {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, Integer> weakMap = new WeakHashMap<>();
        Map<Integer, Integer> hashMap = new HashMap<>();

        Integer k1 = 10, k2 = 20;
        weakMap.put(k1, k1);
        weakMap.put(k2, k2);

        k1 = null;
        k2 = null;

        System.gc();

        Thread.sleep(10000);
        System.out.println(weakMap);



    }
}
