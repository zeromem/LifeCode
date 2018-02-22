package org.zeromem.lifecode.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zeromem
 * @date 2018/2/12
 */
public class UseUnmodifiableMap {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);

        Map<Integer, Integer> um = Collections.unmodifiableMap(map);

        // 可以通过map修改底层内容
        map.put(3, 3);
        System.out.println(um);

        try {
            um.put(10, 10);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }
}
