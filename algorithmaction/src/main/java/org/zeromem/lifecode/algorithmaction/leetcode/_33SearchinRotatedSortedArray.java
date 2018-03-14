package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/13
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 */
public class _33SearchinRotatedSortedArray {
    public static void main(String[] args) {
        _33SearchinRotatedSortedArray test = new _33SearchinRotatedSortedArray();
        int[] nums = new int[]{1};
        int target = 0;
        int result = test.search(nums, target);
        System.out.println(result);
    }

    public int search(int[] nums, int target) {
        int lowest = findLowestPos(nums);
        int half1 = binarySearch(nums, 0, lowest - 1, target);
        if (half1 >= 0) {
            return half1;
        }
        int half2 = binarySearch(nums, lowest, nums.length - 1, target);
        if (half2 >= 0) {
            return half2;
        }
        return -1;

    }

    public static int binarySearch(int[] nums, int start, int end, int target) {
        int l = start, h = end;
        while (l <= h) {
            int mid = (l + h) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return -(l + 1);
    }

    public static int findLowestPos(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int mid = (l + h) >> 1;
            if (nums[mid] > nums[h]) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }
        return l;
    }
}
