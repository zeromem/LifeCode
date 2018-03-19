package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zeromem
 * @date 2018/3/19
 * Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
 * <p>
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 */
public class _163MissingRanges {
    public static void main(String[] args) {
        _163MissingRanges test = new _163MissingRanges();
        int[] A = new int[]{0, 1, 3, 50, 75};
        System.out.println(test.findMissingRanges(A, 0, 99));
    }

    public List<String> findMissingRanges(int[] A, int lower, int upper) {
        List<String> result = new LinkedList<>();
        if (lower < A[0]) {
            if (lower == A[0] - 1) {
                result.add(String.valueOf(lower));
            } else {
                result.add(lower + "->" + (A[0] - 1));
            }
        }

        for (int i = 1; i < A.length; i++) {
            if (A[i] == A[i - 1] + 1) {
                continue;
            }
            if (A[i] == A[i - 1] + 2) {
                result.add(String.valueOf(A[i] - 1));
            } else {
                result.add((A[i - 1] + 1) + "->" + (A[i] - 1));
            }
        }

        if (upper > A[A.length - 1]) {
            if (upper == A[A.length - 1] + 1) {
                result.add(String.valueOf(upper));
            } else {
                result.add((A[A.length - 1] + 1) + "->" + upper);
            }
        }
        return result;
    }
}
