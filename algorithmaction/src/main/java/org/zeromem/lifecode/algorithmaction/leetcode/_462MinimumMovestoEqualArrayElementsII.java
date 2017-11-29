package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/11/29
 * <p>
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
 * <p>
 * You may assume the array's length is at most 10,000.
 * <p>
 * Example:
 * <p>
 * Input:
 * [1,2,3]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element):
 * <p>
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 */
public class _462MinimumMovestoEqualArrayElementsII {
    public static void main(String[] args) {

    }

    public int minMoves2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int n = nums.length;
        int t = 0;
        for (int i = 0; i < n; i++) {
            t += nums[i];
        }
        long avg = Math.round(Double.valueOf(t) / n);
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.abs(avg - nums[i]);
        }

        return res;

    }
}
