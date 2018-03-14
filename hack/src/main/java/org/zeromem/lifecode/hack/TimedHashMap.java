package org.zeromem.lifecode.hack;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author zeromem
 * @date 2018/3/14
 * Google Collections, or Guava as it is named now has something called MapMaker
 * which can do exactly that.
 */
public class TimedHashMap {
    public static void main(String[] args) throws InterruptedException {
        // expireAfterWrite(t): 写入到cache，保留t时间，不管t时间内是否有get访问，都将被移除。只与初始插入时间相关
        // expireAfterAccess(t): 每次被访问后，都将重新计时t
        Cache<Integer, Integer> cache = CacheBuilder.newBuilder()
                .concurrencyLevel(4)
//                .weakKeys() // 表示key比较的时候用==，而不是equals
                .maximumSize(10)
//                .expireAfterWrite(3, TimeUnit.SECONDS)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build();

        // 运行时类型为LocalCache$LocalManualCache
        System.out.println(cache.getClass());

        for (int i = 0; i < 100; i++) {
            cache.put(i, i);
            if (i % 9 == 0) {
                // 通过get操作，可以刷新对旧entry的访问，在超过max size时，会淘汰最早被访问的
                cache.getIfPresent(1);
            }
        }
        System.out.println(cache.asMap());

        Thread.sleep(1000);
        cache.getIfPresent(1);
//        cache.put(1, 1); // 如果是expireAfterWrite,则用put刷新
        cache.put(200, 200);
        System.out.println(cache.asMap());

        Thread.sleep(1000);
        cache.getIfPresent(1);
        System.out.println(cache.asMap());

        Thread.sleep(1000);
        cache.getIfPresent(1);
        System.out.println(cache.asMap());
    }
}
