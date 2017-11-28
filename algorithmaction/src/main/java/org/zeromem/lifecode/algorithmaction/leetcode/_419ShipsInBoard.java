package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * Created by zeromem on 2016/11/3.
 */
public class _419ShipsInBoard {
    /**
     * 板上的船只会是横着或竖着，且不会出现相连接的情况
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        // 扫两遍，横竖各一遍
        int height = board.length;
        int width = board[0].length;
        int cAll = 0, cH = 0, cS = 0;

        // 横
        for (int i = 0; i < height; i++) {
            boolean flag = false;
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'X') {
                    cAll++;
                    if (!flag) {
                        flag = true;
                        cH++;
                    }
                } else {
                    flag = false;
                }
            }
        }

        for (int i = 0; i < width; i++) {
            boolean flag = false;
            for (int j = 0; j < height; j++) {
                if (board[j][i] == 'X') {
                    if (!flag) {
                        flag = true;
                        cS++;
                    }
                } else {
                    flag = false;
                }
            }
        }

        return cH + cS - cAll;
    }

    public int countBattleships2(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X' && (i == 0 || board[i-1][j] == '.') && (j == 0 || board[i][j-1] == '.')) {
                    count++;
                }
            }
        }
        return count;
    }
}
