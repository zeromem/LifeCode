package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zeromem
 * @date 2017/12/8
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 * <p>
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 * <p>
 * Example 1:
 * Input: [1, 2, 2, 3, 1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * Example 2:
 * Input: [1,2,2,3,1,4,2]
 * Output: 6
 * Note:
 * <p>
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 */
public class _697DegreeofanArray {
    public static void main(String[] args) {
        _697DegreeofanArray test = new _697DegreeofanArray();
        int[] nums = new int[]{1, 2, 2, 3, 1, 4, 2};
        System.out.println(test.findShortestSubArray(nums));
    }

    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new int[]{1, i, i});
            } else {
                int[] arr = map.get(nums[i]);
                arr[0]++;
                arr[2] = i;
            }
        }

        int degree = Integer.MIN_VALUE, result = Integer.MAX_VALUE;
        for (int[] pair : map.values()) {
            if (pair[0] > degree) {
                degree = pair[0];
                result = pair[2] - pair[1] + 1;
            } else if (pair[0] == degree && pair[2] - pair[1] + 1 < result) {
                result = pair[2] - pair[1] + 1;
            }
        }
        return result;
    }
}
