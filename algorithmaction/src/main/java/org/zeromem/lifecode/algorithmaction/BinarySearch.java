package org.zeromem.lifecode.algorithmaction;

/**
 * @author zeromem
 * @date 2017/12/19
 */
public class BinarySearch {
    public static void main(String[] args) {
//        int[] a = new int[]{0, 1, 2, 3, 5, 7, 9, 13, 16, 19, 36, 39, 67, 69, 80, 89, 90, 91, 95, 96, 99};
        int[] a = new int[]{3, 6, 8};
        System.out.println(binarySearch(a, 7));
    }

    public static int binarySearch(int[] a, int key) {
        return binarySearch0(a, 0, a.length, key);
    }

    public static int binarySearch0(int[] a, int start, int end, int key) {
        int low = start, high = end - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];
            if (key > midVal) {
                low = mid + 1;
            } else if (key < midVal) {
                high = mid - 1;
            } else {
                return mid;
            }
            System.out.println(mid);
        }
        return -(low + 1);
    }
}
