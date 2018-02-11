package org.zeromem.lifecode.algorithmaction.july;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2018/1/5
 * 数组中最小的k个数
 */
public class TopKNumbers {
    public static void main(String[] args) {
        TopKNumbers test = new TopKNumbers();
        int[] nums = new int[]{413, 1, 6, 333, 9, 8, 45, 213, 90};
        System.out.println(Arrays.toString(test.topk(nums, 3)));

    }

    public int[] topk(int[] nums, int k) {
        int[] headK = new int[k];
        for (int i = 0; i < k; i++) {
            headK[i] = nums[i];
        }
        MinHeap heap = new MinHeap(headK);
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > heap.getRoot()) {
                heap.setRoot(nums[i]);
            }
        }
        return headK;
    }
}
