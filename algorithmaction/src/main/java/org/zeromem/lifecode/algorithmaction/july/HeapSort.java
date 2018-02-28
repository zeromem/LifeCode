package org.zeromem.lifecode.algorithmaction.july;

import java.util.Random;

/**
 * @author zeromem
 * @date 2018/2/28
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] a = new Random().ints(100000, 0, 1000000).toArray();
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
        for (int i = start; i <= end; i++) {
            int mid = (i + end) >> 1;
            for (int j = mid; j >= i; j--) {
                heapify(a, i, end, j);
            }
        }
    }

    public static void heapify(int[] a, int start, int end, int i) {
        int smallest = i;
        int left = left(start, i);
        int right = right(start, i);

        if (left <= end && a[left] < a[smallest]) {
            smallest = left;
        }

        if (right <= end && a[right] < a[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            swap(a, i, smallest);
            heapify(a, start, end, smallest);
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
