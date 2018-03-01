package org.zeromem.lifecode.algorithmaction;

import org.zeromem.lifecode.algorithmaction.july.HeapSort;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by zeromem on 2017/5/5.
 */
public class MergeSort {
	public static void mergeSort(int[] input, int left, int right) {
		int mid = (left + right) / 2;
		if (left < right) {
			mergeSort(input, left, mid);
			mergeSort(input, mid + 1, right);
			merge2(input, left, mid, right);
		}
	}

    public static void merge2(int[] a, int start, int mid, int end) {
        if (start == end) {
            return;
        }
        int l = start, h = mid + 1;

        int[] arr = new int[end - start + 1];
        int pos = 0;
        while (l <= mid && h <= end) {
            arr[pos++] = a[l] < a[h] ? a[l++] : a[h++];
        }
        while (l <= mid) {
            arr[pos++] = a[l++];
        }
        while (h <= end) {
            arr[pos++] = a[h++];
        }

        pos = 0;
        for (int i = start; i <= end; i++) {
            a[i] = arr[pos++];
        }
    }



}
