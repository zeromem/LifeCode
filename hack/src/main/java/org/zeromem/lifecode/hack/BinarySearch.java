package org.zeromem.lifecode.hack;

import java.util.Arrays;

/**
 * Created by zeromem on 2017/9/8.
 */
public class BinarySearch {
	public static void main(String[] args) {
		int x ;
		// ...
		// x == -x, 则x是0或Integer.MIN_VALUE.


		Integer a = Integer.MAX_VALUE;
		Integer b = Integer.MIN_VALUE;
		System.out.println(average(a, b));
	}



	public static Integer average(Integer a, Integer b) {
		if (a > 0 && b > 0) {
			return (a + b) >>> 1;
		} else if ((a | b & Integer.MIN_VALUE) != 0) {
			return (a + b) / 2;
		} else if (a != Integer.MIN_VALUE && b != Integer.MIN_VALUE){
			return -((-a + -b) >>> 1);
		} else if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
			return a;
		} else if (a == Integer.MIN_VALUE) {
			return average(a + 2, b) - 1;
		} else {
			return average(a, b + 2) - 1;
		}
	}

	public static Integer binarySearch(int[] arr, int key) {
		int low = 0;
		int high = arr.length - 1;
		while (low <= high) {
			int mid = (low + high) >>> 1;
			int _val = arr[mid];
			if (key > _val) {
				low = mid + 1;
			} else if (key < _val) {
				high = mid - 1;
			} else if (key == _val) {
				return mid;
			}
		}
		return -(low + 1); // why.
	}
}
