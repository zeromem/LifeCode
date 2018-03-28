package org.zeromem.lifecode.hack.effectivejava;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

/**
 * @author zeromem
 * @date 2018/2/11
 * 如果有合适的接口存在，则参数、返回值、变量和域都应该使用接口来声明。
 * 创建对象时才需要引用具体对象的类。
 * 例外：
 * 1.值类通常很适合作为参数、返回值...
 * 如String, StringBuilder，通常不会实现接口，也通常是final的。
 * 2.基于类的框架中。应该使用相关基类，而不是实现类。
 * 3.需要用到实现类提供的接口额外的功能。如LinkedHashMap
 */
public class _40UseInterfaceBetterThanClass {
    public static void main(String[] args) {
        List<String> vector = new Vector<>();
//        不要用Vector声明变量，用List
//        Vector<String> vector1 = new Vector<>();

        // LinkedHashMap提供了Map额外的功能，且方法用到了这些额外功能，则参数必须是具体类
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
        useLinkedHashMap(linkedHashMap);

    }

    public static void useLinkedHashMap(LinkedHashMap<String, String> linkedHashMap) {
        linkedHashMap.put("1", "2");
        linkedHashMap.put("3", "30");
        linkedHashMap.put("2", "20");
        linkedHashMap.put("1", "10");
        System.out.println(linkedHashMap);
    }

}
