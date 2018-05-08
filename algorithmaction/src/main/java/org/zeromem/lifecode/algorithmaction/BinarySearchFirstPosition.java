package org.zeromem.lifecode.algorithmaction;

/**
 * @author zeromem
 * @date 2018/3/30
 */
public class BinarySearchFirstPosition {
    public static void main(String[] args) {
        BinarySearchFirstPosition test = new BinarySearchFirstPosition();
        int[] a = new int[]{2, 3, 3, 3, 3, 3, 3, 3, 3, 5, 6};
        System.out.println(test.binarySearch(a, 3));
    }

    public int binarySearch(int[] a, int target) {
        return binarySearch(a, target, 0, a.length - 1);
    }

    public int binarySearch(int[] a, int target, int start, int end) {
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (target == a[mid]) {
                int headResult = binarySearch(a, target, start, mid - 1);
                if (headResult != -1) {
                    return headResult;
                } else {
                    return mid;
                }
            } else if (target > a[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
