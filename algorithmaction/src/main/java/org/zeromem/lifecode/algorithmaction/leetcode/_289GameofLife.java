package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2018/3/12
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 */
public class _289GameofLife {
    public static void main(String[] args) {
        _289GameofLife test = new _289GameofLife();
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        test.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
    }

    public void gameOfLife(int[][] board) {
        int[][] res = new int[board.length][board[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                int sum = neiSum(board, i, j);
                if (board[i][j] == 0) {
                    if (sum == 3) {
                        res[i][j] = 1;
                    }
                } else {
                    if (sum == 2 || sum == 3) {
                        res[i][j] = 1;
                    } else {
                        res[i][j] = 0;
                    }
                }
            }

        }
        for (int i = 0; i < board.length; i++) {
            board[i] = Arrays.copyOf(res[i], res[i].length);
        }
    }

    public static int neiSum(int[][] board, int i, int j) {
        int sum = 0;
        if (i - 1 >= 0 && j - 1 >= 0) {
            sum += board[i - 1][j - 1];
        }
        if (i - 1 >= 0) {
            sum += board[i - 1][j];
        }
        if (i - 1 >= 0 && j + 1 < board[0].length) {
            sum += board[i - 1][j + 1];
        }
        if (j - 1 >= 0) {
            sum += board[i][j - 1];
        }
        if (j + 1 < board[0].length) {
            sum += board[i][j + 1];
        }
        if (i + 1 < board.length && j - 1 >= 0) {
            sum += board[i + 1][j - 1];
        }
        if (i + 1 < board.length) {
            sum += board[i + 1][j];
        }
        if (i + 1 < board.length && j + 1 < board[0].length) {
            sum += board[i + 1][j + 1];
        }
        return sum;
    }
}
