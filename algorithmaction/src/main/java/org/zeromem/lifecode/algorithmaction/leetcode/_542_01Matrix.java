package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2018/3/1
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two adjacent cells is 1.
 * Example 1:
 * Input:
 * <p>
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * Output:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 */
public class _542_01Matrix {
    public static void main(String[] args) {
        _542_01Matrix test = new _542_01Matrix();
        int[][] a = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] res = test.updateMatrix(a);
        System.out.println(Arrays.deepToString(res));
    }

    public int[][] updateMatrix(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, result[i], 0, matrix[0].length);
        }

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                if (result[i][j] == 1) {
                    result[i][j] = Integer.MAX_VALUE;
                    if (i - 1 >= 0 && result[i - 1][j] != Integer.MAX_VALUE) {
                        result[i][j] = Math.min(result[i][j], 1 + result[i - 1][j]);
                    }
                    if (j - 1 >= 0 && result[i][j - 1] != Integer.MAX_VALUE) {
                        result[i][j] = Math.min(result[i][j], 1 + result[i][j - 1]);
                    }
                }
            }
        }

        for (int i = result.length - 1; i >= 0; i--) {
            for (int j = result[0].length - 1; j >= 0; j--) {
                if (i + 1 < result.length && result[i + 1][j] != Integer.MAX_VALUE) {
                    result[i][j] = Math.min(result[i][j], 1 + result[i + 1][j]);
                }

                if (j + 1 < result[0].length && result[i][j + 1] != Integer.MAX_VALUE) {
                    result[i][j] = Math.min(result[i][j], 1 + result[i][j + 1]);
                }
            }
        }

        return result;
    }
}
