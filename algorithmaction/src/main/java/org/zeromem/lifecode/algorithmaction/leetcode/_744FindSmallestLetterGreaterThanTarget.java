package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/12/19
 * 利用二分查找
 * 因为要找大于target的最小字母，
 * 因此二分条件应该设置为mid_char<=target
 * 保证有重复字符时，mid会一直向后调整。
 */
public class _744FindSmallestLetterGreaterThanTarget {
    public static void main(String[] args) {
        _744FindSmallestLetterGreaterThanTarget test = new _744FindSmallestLetterGreaterThanTarget();
        char[] letters = new char[]{'c', 'f', 'j'};
        char res = test.nextGreatestLetter(letters, 'a');
        System.out.println(res);
    }
    public char nextGreatestLetter(char[] letters, char target) {
        int low = 0, high = letters.length - 1;
        int mid = low;
        while (low <= high) {
            mid = (low + high) >> 1;
            if (letters[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return letters[mid % letters.length];
    }
}
