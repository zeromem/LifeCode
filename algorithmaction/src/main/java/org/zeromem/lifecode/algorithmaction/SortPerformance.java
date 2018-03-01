package org.zeromem.lifecode.algorithmaction;

import org.zeromem.lifecode.algorithmaction.july.HeapSort;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


/**
 * @author zeromem
 * @date 2018/3/1
 * 排序算法性能比较 归并排序、堆排序、快速排序
 * 堆排序性能最差.
 */
public class SortPerformance {
    public static void main(String[] args) throws IOException {
        int[] a = new Random().ints(10000000).toArray();
        int[] mergeArr = Arrays.copyOf(a, a.length);
        int[] heapArr = Arrays.copyOf(a, a.length);
        int[] quickArr = Arrays.copyOf(a, a.length);

        long mergeStart = System.currentTimeMillis();
        MergeSort.mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.printf("merge sort result %b, cost %d\n", checkSorted(mergeArr), System.currentTimeMillis() - mergeStart);

        long heapStart = System.currentTimeMillis();
        HeapSort.heapSort(heapArr, 0, heapArr.length - 1);
        System.out.printf("heap sort result %b, cost %d\n", checkSorted(heapArr), System.currentTimeMillis() - heapStart);

        long quickStart = System.currentTimeMillis();
        QuickSort.quickSort1(quickArr, 0, quickArr.length - 1);
        System.out.printf("quick sort result %b, cost %d\n", checkSorted(quickArr), System.currentTimeMillis() - quickStart);
    }

    public static boolean checkSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    }

}
