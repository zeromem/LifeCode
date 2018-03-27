package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/21
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * <p>
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class _169MajorityElement {
    public static void main(String[] args) {

    }

    public int majorityElement(int[] nums) {
        Double.valueOf(Math.sqrt(10)).intValue();
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length <= 2) {
            return nums[0];
        }
        int count = 1, major = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == major) {
                count++;
            } else if (count == 0) {
                count = 1;
                major = nums[i];
            } else {
                count--;
            }
        }
        return major;
    }
}
