package org.zeromem.lifecode.algorithmaction;

/**
 * Created by zeromem on 2017/2/17.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {6, 5, 7, 4, 11, 2, 3, 9};
//        int[] a = {5, 3};
        qs(a, 0, 7);
    }

    public static void quicksort(int[] A, int start, int end) {
        for (int e : A) {
            System.out.print(e + " ");
        }
        System.out.println();
        if (start >= end) return;
        int p = A[start];
        int i = start + 1;
        int j = end;
        while (i < j) {
            while (i < j && A[i] <= p) {
                i++;
            }
            while (i < j && A[j] > p) {
                j--;
            }
            swap(A, i, j);
        }
        while (A[i] > p) {
            i--;
        }
        swap(A, start, i);

        quicksort(A, start, i - 1);
        quicksort(A, i + 1, end);
    }


    public static void qs(int[] A, int start, int end) {
        for (int e : A) {
            System.out.print(e + " ");
        }
        System.out.println();
        if (start >= end) return;
        int p = A[start];
        int s = start;
        for (int i = start + 1; i <= end; i++) {
            if (A[i] <= p) {
                s++;
                swap(A, s, i);
            }
        }
        swap(A, start, s);

        qs(A, start, s - 1);
        qs(A, s + 1, end);
    }

    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
