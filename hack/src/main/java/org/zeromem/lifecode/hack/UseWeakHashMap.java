package org.zeromem.lifecode.hack;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author zeromem
 * @date 2018/2/11
 */
public class UseWeakHashMap {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> weakMap = new WeakHashMap<>();

        Integer k1 = 10;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15000000; i++) {
            sb.append(i);
        }
        String l = sb.toString();


        weakMap.put(k1, l);

        k1 = null;
        l = null;

        System.gc();

        Thread.sleep(8000);
        System.out.println(weakMap);



    }
}
