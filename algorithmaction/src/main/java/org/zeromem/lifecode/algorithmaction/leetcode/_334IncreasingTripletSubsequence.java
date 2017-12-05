package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/11/30
 * 遍历数组，记录最小的数min，和min之后最小的数secondMin。
 * 若发现后面某个数比secondMin大，则返回true
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * <p>
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * <p>
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 * <p>
 * Given [5, 4, 3, 2, 1],
 * return false.
 */
public class _334IncreasingTripletSubsequence {
    public static void main(String[] args) {
        _334IncreasingTripletSubsequence test = new _334IncreasingTripletSubsequence();
        int[] nums = new int[]{1, 2, 2, 1};
        System.out.println(test.increasingTriplet(nums));
    }

    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= min) {
                min = num;
            } else if (num <= secondMin) {
                secondMin = num;
            } else {
                return true;
            }
        }
        return false;

    }
}
