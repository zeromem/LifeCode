package org.zeromem.lifecode.algorithmaction;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by zeromem on 2017/2/17.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = new Random().ints(10000000).toArray();
        int[] b = Arrays.copyOf(a, a.length);
        int[] c = Arrays.copyOf(a, a.length);

        long s1 = System.currentTimeMillis();
        quickSort1(a, 0, a.length - 1);
        System.out.println(System.currentTimeMillis() - s1);

        long s2 = System.currentTimeMillis();
        quickSort2(b, 0, b.length - 1);
        System.out.println(System.currentTimeMillis() - s2);

        long s3 = System.currentTimeMillis();
        Arrays.sort(c);
        System.out.println(System.currentTimeMillis() - s3);

        System.out.println(checkSorted(a));
        System.out.println(checkSorted(b));
        System.out.println(checkSorted(c));
    }

    public static boolean checkSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void quickSort1(int[] A, int start, int end) {
        if (start >= end) return;
        int s = partition1(A, start, end);
        quickSort1(A, start, s - 1);
        quickSort1(A, s + 1, end);
    }

    public static void quickSort2(int[] A, int start, int end) {
        if (start >= end) return;
        int s = partition2(A, start, end);
        quickSort2(A, start, s - 1);
        quickSort2(A, s + 1, end);
    }

    public static int partition1(int[] A, int start, int end) {
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

    public static int partition2(int[] A, int start, int end) {
        if (start == end) {
            return start;
        }
        int p = A[start];
        while (start < end) {
            while (A[end] >= p && start < end) {
                end--;
            }
            A[start] = A[end];

            while (A[start] <= p && start < end) {
                start++;
            }
            A[end] = A[start];
        }
        A[start] = p;
        return start;
    }

    public static void swap(int[] A, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
