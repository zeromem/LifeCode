package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2018/3/12
 */
public class _673NumberofLongestIncreasingSubsequence {
    public static void main(String[] args) {
        _673NumberofLongestIncreasingSubsequence test = new _673NumberofLongestIncreasingSubsequence();
        int[] nums = new int[]{1, 3, 5, 4, 7};
//        nums = new int[]{2, 2, 2, 2, 2};
        int result = test.findNumberOfLIS(nums);
        System.out.println(result);
    }

    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        int[] lengths = new int[nums.length];
        int[] counts = new int[nums.length];
        Arrays.fill(counts, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    if (lengths[j] >= lengths[i]) {
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    } else if (lengths[j] == lengths[i] - 1) {
                        counts[i] += counts[j];
                    }
                }
            }
        }
        int maxLen = 0;
        for (int i = 0; i < lengths.length; i++) {
            if (lengths[i] > maxLen) {
                maxLen = lengths[i];
            }
        }
        int result = 0;
        for (int i = 0; i < counts.length; i++) {
            if (lengths[i] == maxLen) {
                result += counts[i];
            }
        }
        return result;
    }
}
