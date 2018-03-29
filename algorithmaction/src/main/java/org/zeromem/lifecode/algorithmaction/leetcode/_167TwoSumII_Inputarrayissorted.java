package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/28
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。请注意，返回的下标值（index1 和 index2）不是从零开始的。
 * <p>
 * 你可以假设每个输入都只有一个解决方案，而且你不会重复使用相同的元素。
 * <p>
 * 输入：数组 = {2, 7, 11, 15}, 目标数 = 9
 * 输出：index1 = 1, index2 = 2
 */
public class _167TwoSumII_Inputarrayissorted {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) {
            return null;
        }
        if (numbers.length <= 1) {
            return new int[]{-1, -1};
        }
        int start = 0, end = numbers.length - 1;
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) {
                return new int[]{start + 1, end + 1};
            } else if (sum > target) {
                end--;
            } else {
                start++;
            }
        }
        return new int[]{-1, -1};
    }
}
