package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/12/19
 */
public class _744FindSmallestLetterGreaterThanTarget {
    public static void main(String[] args) {
        _744FindSmallestLetterGreaterThanTarget test = new _744FindSmallestLetterGreaterThanTarget();
        char[] letters = new char[]{1, 3, 5, 7, 9};
        System.out.println(test.nextGreatestLetter(letters, 'e'));
    }
    public char nextGreatestLetter(char[] letters, char target) {
        int pos = binarySearch0(letters, 0, letters.length, target);
        if (pos == letters.length) {
            return letters[0];
        }
        return letters[pos];
    }

    public static int binarySearch0(char[] a, int start, int end, char key) {
        int low = start, high = end - 1;
        int mid = low;
        int direct = 1;
        while (low <= high) {
            System.out.println(a[low] + "--" + a[high]);
            mid = (low + high) >>> 1;
            char midChar = a[mid];
            if (midChar == key) {
                direct = 1;
                break;
            } else if (key > midChar) {
                direct = 1;
                low = mid + 1;
            } else {
                direct = 0;
                high = mid - 1;
            }
        }
        return mid + direct;
    }
}
