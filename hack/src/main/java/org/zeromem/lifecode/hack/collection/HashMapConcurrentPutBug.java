package org.zeromem.lifecode.hack.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author zeromem@qq.com
 * @date 2018/3/14
 * hashmap 并发put操作导致死循环
 */
public class HashMapConcurrentPutBug {
    public static void main(String[] args) throws InterruptedException {
        // 一种更不易被发现的错误使用
        Map<Integer, Integer> imap = new HashMap<>();
        new Random().ints(1000000).parallel().forEach((x) -> {
            imap.put(x % 10, x);
        });
        System.out.println(imap.size());
        System.exit(1);


        final Map<String, Integer> map = new HashMap<>();
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                map.put(UUID.randomUUID().toString(), 1);
            }
        };

        Thread t = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                new Thread(task).start();
            }
        });

        t.start();
        t.join();



    }
}
