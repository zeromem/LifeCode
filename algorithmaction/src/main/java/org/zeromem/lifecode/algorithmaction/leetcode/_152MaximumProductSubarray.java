package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/12/8
 * <p>
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */
public class _152MaximumProductSubarray {
    public static void main(String[] args) {

    }

    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length;
        int[] max = new int[n];
        int[] min = new int[n];
        int prod = nums[0];
        max[0] = min[0] = nums[0];

        for (int i = 1; i < n; i++) {
            max[i] = Math.max(nums[i], Math.max(nums[i] * max[i - 1], nums[i] * min[i - 1]));
            min[i] = Math.min(nums[i], Math.min(nums[i] * min[i - 1], nums[i] * max[i - 1]));
            if (max[i] > prod) {
                prod = max[i];
            }
        }
        return prod;
    }
}
