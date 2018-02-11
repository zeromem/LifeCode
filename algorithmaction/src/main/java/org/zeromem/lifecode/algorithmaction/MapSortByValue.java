package org.zeromem.lifecode.algorithmaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zeromem
 * @date 2018/1/17
 */
public class MapSortByValue {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("hello", 10);
        map.put("world", 1);
        map.put("good", 17);
        map.put("bye", 9);
        System.out.println(map);
        System.out.println(sortByValue(map));

        // java8 Map.Entry#comparingByValue
        Stream<Map.Entry<String, Integer>> sorted
                = map.entrySet().stream().sorted(Map.Entry.comparingByValue());
        System.out.println(sorted.collect(Collectors.toList()));

    }

    public static List<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        list.addAll(map.entrySet());
        list.sort((e1, e2) -> {
            return e1.getValue() - e2.getValue();
        });
        return list;
    }
}
