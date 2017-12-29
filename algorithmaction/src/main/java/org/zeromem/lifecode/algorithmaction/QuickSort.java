package org.zeromem.lifecode.algorithmaction;

import java.util.Arrays;

/**
 * Created by zeromem on 2017/2/17.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {6, 5, 7, 4, 11, 2, 3, 9};
        qs(a, 0, 7);
        System.out.println(Arrays.toString(a));
    }

    public static void qs(int[] A, int start, int end) {
        if (start >= end) return;
        int s = partition(A, start, end);
        qs(A, start, s - 1);
        qs(A, s + 1, end);
    }

    public static int partition(int[] A, int start, int end) {
        int p = A[start];
        int s = start;
        for (int i = start + 1; i <= end; i++) {
            if (A[i] <= p) {
                s++;
                swap(A, s, i);
            }
        }
        swap(A, start, s);
        return s;
    }

    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
