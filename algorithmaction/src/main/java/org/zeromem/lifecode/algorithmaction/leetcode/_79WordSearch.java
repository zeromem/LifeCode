package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.*;

/**
 * @author zeromem@qq.com
 * @date 2018/3/15
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * For example,
 * Given board =
 * <p>
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */
public class _79WordSearch {
    public static void main(String[] args) {
        _79WordSearch test = new _79WordSearch();
        char[][] board = new char[][]{{'a', 'a'}};
        String word = "aaa";
        System.out.println(test.exist(board, word));
    }
    public boolean exist(char[][] board, String word) {
        // if (board == null || word == null || board.length * board[0].length < word.length()) {
        //     return false;
        // }

        char[] target = word.toCharArray();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, visited, i, j, target,0)) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * 将board[i][j]和target[pos]比较.递归的搜索比较board[i+-1][j+-1]和target[pos+1]。
     * 直至pos==target.length，表明搜索成功
     * @param board
     * @param visited
     * @param i
     * @param j
     * @param target
     * @param pos
     * @return
     */
    @SuppressWarnings("Duplicates")
    public static boolean dfs(char[][] board, boolean[][] visited,
                              int i, int j, char[] target, int pos) {
        // 搜索比较到了第pos个字符，说明全部比较完毕
        if (pos == target.length) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        if (board[i][j] != target[pos]) {
            visited[i][j] = false;
            return false;
        }
        boolean existNext = dfs(board, visited, i - 1, j, target, pos + 1)
                || dfs(board, visited, i + 1, j, target, pos + 1)
                || dfs(board, visited, i, j - 1, target, pos + 1)
                || dfs(board, visited, i, j + 1, target, pos + 1);
        visited[i][j] = false;
        return existNext;
    }
}