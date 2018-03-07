package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/6
 * We are given an array A of positive integers, and two positive integers L and R (L <= R).
 * <p>
 * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.
 * <p>
 * Example :
 * Input:
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 * Note:
 * <p>
 * L, R  and A[i] will be an integer in the range [0, 10^9].
 * The length of A will be in the range of [1, 50000].
 */
public class _795NumberofSubarrayswithBoundedMaximum {
    public static void main(String[] args) {
        _795NumberofSubarrayswithBoundedMaximum test = new _795NumberofSubarrayswithBoundedMaximum();
        int[] A = new int[]{2, 1, 4, 3};
        int L = 2, R = 3;
        int res = test.numSubarrayBoundedMax(A, L, R);
        System.out.println(res);
    }

    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int res = 0, count = 0, j = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= L && A[i] <= R) {
                count = i - j + 1;
                res += count;
            } else if (A[i] < L) {
                res += count;
            } else {
                count = 0;
                j = i + 1;
            }
        }
        return res;
    }
}
