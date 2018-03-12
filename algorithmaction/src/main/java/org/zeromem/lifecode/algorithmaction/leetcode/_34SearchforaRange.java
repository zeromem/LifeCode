package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;

/**
 * @author zeromem@qq.com
 * @date 2018/3/12
 */
public class _34SearchforaRange {
    public static void main(String[] args) {
        _34SearchforaRange test = new _34SearchforaRange();
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        nums = new int[]{2, 2};
        int target = 1;
        int[] result = test.searchRange(nums, target);
        System.out.println(Arrays.toString(result));
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};

        if (nums == null || nums.length == 0) {
            return result;
        }
        if (nums.length == 1) {
            if (target == nums[0]) {
                return new int[]{0, 0};
            } else {
                return result;
            }
        }
        int left = leftBound(nums, target);
        if (left == -1) {
            return result;
        }
        if (left >= nums.length || nums[left] != target) {
            return result;
        }

        return new int[]{left, rightBound(nums, target)};
    }

    /**
     * target应该出现在nums中的位置。target可能不存在nums中
     *
     * @param nums
     * @param target
     * @return
     */
    public static int leftBound(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int mid = (l + h) >> 1;
            if (target <= nums[mid]) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static int rightBound(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int mid = (l + h + 1) >> 1;
            if (target >= nums[mid]) {
                l = mid;
            } else {
                h = mid - 1;
            }
        }
        return h;
    }
}
