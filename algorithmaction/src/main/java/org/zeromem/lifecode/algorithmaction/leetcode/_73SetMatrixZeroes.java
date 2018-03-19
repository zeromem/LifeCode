package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zeromem
 * @date 2018/3/19
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 */
public class _73SetMatrixZeroes {
    public static void main(String[] args) {

    }

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return;
        }
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (zeroRows.contains(i)) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            if (zeroCols.contains(j)) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
