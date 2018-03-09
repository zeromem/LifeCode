package org.zeromem.lifecode.algorithmaction.july;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zeromem
 * @date 2018/2/28
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] a = new Random().ints(10000000, 0, 1000).toArray();
        long s = System.currentTimeMillis();
        heapSort(a, 0, a.length - 1);
        System.out.println(System.currentTimeMillis() - s);
        System.out.println(checkSorted(a));
    }

    public static boolean checkSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void heapSort(int[] a, int start, int end) {
        for (int i = (start + end) / 2; i >= start; i--) {
            heapify(a, start, end, i);
        }
        for (int i = end; i > start; i--) {
            swap(a, start, i);
            heapify(a, start, i - 1, start);
        }
    }

    public static void heapify(int[] a, int start, int end, int i) {
        int biggest = i;
        int left = left(start, i);
        int right = right(start, i);

        if (left <= end && a[left] > a[biggest]) {
            biggest = left;
        }

        if (right <= end && a[right] > a[biggest]) {
            biggest = right;
        }

        if (biggest != i) {
            swap(a, i, biggest);
            heapify(a, start, end, biggest);
        }
    }

    // 获取右结点的数组下标
    private static int right(int start, int i) {
        return ((i + 1) << 1) - start;
    }

    // 获取左结点的数组下标
    private static int left(int start, int i) {
        return ((i + 1) << 1) - 1 - start;
    }

    // 交换元素位置
    private static void swap(int[] heap, int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
