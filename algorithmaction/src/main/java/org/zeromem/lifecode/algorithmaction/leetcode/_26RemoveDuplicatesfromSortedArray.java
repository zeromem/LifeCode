package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2018/3/19
 * Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example:
 * <p>
 * Given nums = [1,1,2],
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 */
public class _26RemoveDuplicatesfromSortedArray {
    public static void main(String[] args) {
        _26RemoveDuplicatesfromSortedArray test = new _26RemoveDuplicatesfromSortedArray();
        int[] nums = new int[]{1, 2};
        System.out.println(test.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        int base = 0;
        int pos = 1;
        int count = 1;
        while (pos < nums.length) {
            if (nums[pos] == nums[base]) {
                pos++;
            } else {
                count++;
                nums[++base] = nums[pos];
            }
        }
        return count;
    }
}
