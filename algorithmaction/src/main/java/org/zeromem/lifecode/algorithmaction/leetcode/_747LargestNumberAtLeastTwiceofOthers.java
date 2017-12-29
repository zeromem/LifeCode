package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/12/28
 * <p>
 * In a given integer array nums, there is always exactly one largest element.
 * <p>
 * Find whether the largest element in the array is at least twice as much as every other number in the array.
 * <p>
 * If it is, return the index of the largest element, otherwise return -1.
 * <p>
 * Example 1:
 * Input: nums = [3, 6, 1, 0]
 * Output: 1
 * Explanation: 6 is the largest integer, and for every other number in the array x,
 * 6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
 * Example 2:
 * Input: nums = [1, 2, 3, 4]
 * Output: -1
 * Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
 * Note:
 * nums will have a length in the range [1, 50].
 * Every nums[i] will be an integer in the range [0, 99].
 */
public class _747LargestNumberAtLeastTwiceofOthers {
    public static void main(String[] args) {
        _747LargestNumberAtLeastTwiceofOthers test = new _747LargestNumberAtLeastTwiceofOthers();
//        int[] nums = new int[]{1, 2, 3, 4};
        int[] nums = new int[]{3, 6, 0, 1};
        System.out.println(test.dominantIndex(nums));

    }

    public int dominantIndex(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }

        int max = 0, max2 = 0;
        int pos = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max2 = max;
                max = nums[i];
                pos = i;
            } else if (nums[i] > max2) {
                max2 = nums[i];
            }
        }
        if (max >= (max2 << 1)) {
            return pos;
        } else {
            return -1;
        }
    }
}
