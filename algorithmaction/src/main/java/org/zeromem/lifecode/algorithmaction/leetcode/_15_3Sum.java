package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zeromem
 * @date 2018/3/6
 */
public class _15_3Sum {
    public static void main(String[] args) {
        _15_3Sum test = new _15_3Sum();
        int[] list = new int[]{-1, 0, 1, 2, -1, -4};

        List<List<Integer>> result = test.threeSum(list);
        System.out.println(result);
    }

    public List<List<Integer>> threeSum(int[] list) {
        List<List<Integer>> result = new LinkedList<>();
        if (list == null || list.length <= 2) {
            return result;
        }
        Arrays.sort(list);
        scan3Sum(list, 0, result);
        for (int i = 1; i < list.length - 2; i++) {
            if (list[i] == list[i - 1]) {
                continue;
            }
            scan3Sum(list, i, result);
        }
        return result;
    }

    public static void scan3Sum(int[] list, int pos, List<List<Integer>> result) {
        int l = pos + 1, h = list.length - 1;
        int target = -list[pos];
        while (l < h) {
            int sum = list[l] + list[h];
            if (sum == target) {
                result.add(Arrays.asList(list[pos], list[l], list[h]));
                while (l < h && list[l] == list[++l]);
                while (l < h && list[h] == list[--h]);

            } else if (sum > target) {
                h--;
            } else {
                l++;
            }
        }
    }
}
