package org.zeromem.lifecode.algorithmaction.july;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zeromem
 * @date 2018/3/2
 * 荷兰国旗问题
 * 数组中只有0 1 2三种元素，且乱序。
 * 将数组排序，且只扫描一遍数组。
 */
public class DutchFlag {
    public static void main(String[] args) {
        DutchFlag test = new DutchFlag();
        int[] a = new Random().ints(100, 0, 3).toArray();
        a = new int[]{1, 0};

        test.sortColors(a);
        System.out.println(Arrays.toString(a));
    }

    public void sortColors(int[] nums) {
        int begin = 0, current = 0, end = nums.length - 1;
        while (current <= end) {
            switch (nums[current]) {
                case 0:
                    swap(nums, current, begin);
                    current++;
                    begin++;
                    break;
                case 1:
                    current++;
                    break;
                case 2:
                    swap(nums, current, end);
                    end--;
            }
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
