package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem@qq.com
 * @date 2018/3/12
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * <p>
 * You may assume no duplicates in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,5,6], 5
 * Output: 2
 * Example 2:
 * <p>
 * Input: [1,3,5,6], 2
 * Output: 1
 * Example 3:
 * <p>
 * Input: [1,3,5,6], 7
 * Output: 4
 * Example 1:
 * <p>
 * Input: [1,3,5,6], 0
 * Output: 0
 */
public class _35SearchInsertPosition {
    public static void main(String[] args) {
        _35SearchInsertPosition test = new _35SearchInsertPosition();
        int[] nums = new int[]{1, 3, 5, 6};
        int result = test.searchInsert(nums, 4);
        System.out.println(result);
    }

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int mid = (l + h) >> 1;
            int midVal = nums[mid];
            if (target == midVal) {
                return mid;
            } else if (target < midVal) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
