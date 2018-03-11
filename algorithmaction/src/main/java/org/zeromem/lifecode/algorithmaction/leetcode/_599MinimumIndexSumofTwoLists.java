package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.*;

/**
 * @author zeromem
 * @date 2018/3/7
 * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
 * <p>
 * You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.
 * <p>
 * Example 1:
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * Output: ["Shogun"]
 * Explanation: The only restaurant they both like is "Shogun".
 * Example 2:
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["KFC", "Shogun", "Burger King"]
 * Output: ["Shogun"]
 * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
 * Note:
 * The length of both lists will be in the range of [1, 1000].
 * The length of strings in both lists will be in the range of [1, 30].
 * The index is starting from 0 to the list length minus 1.
 * No duplicates in both lists.
 */
public class _599MinimumIndexSumofTwoLists {
    public static void main(String[] args) {
        _599MinimumIndexSumofTwoLists test = new _599MinimumIndexSumofTwoLists();
        String[] list1 = new String[]{"Shogun","Tapioca Express","Burger King","KFC"};
        String[] list2 = new String[]{"KFC","Shogun","Burger King"};
        String[] restaurant = test.findRestaurant(list1, list2);
        System.out.println(Arrays.toString(restaurant));
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Set<String> intersection = new HashSet<>(Arrays.asList(list1));
        intersection.retainAll(Arrays.asList(list2));
        Map<String, Integer> map = new HashMap<>();
        for (String s : intersection) {
            map.put(s, 0);
        }
        for (int i = 0; i < list1.length; i++) {
            if (map.containsKey(list1[i])) {
                map.put(list1[i], map.get(list1[i]) + i);
            }
        }
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                map.put(list2[i], map.get(list2[i]) + i);
            }
        }
        System.out.println(map);

        int min = Integer.MAX_VALUE;
        for (Integer i : map.values()) {
            if (i < min) {
                min = i;
            }
        }

        List<String> result = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == min) {
                result.add(entry.getKey());
            }
        }
        return result.toArray(new String[0]);
    }
}