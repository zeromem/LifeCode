package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author zeromem
 * @date 2018/3/16
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p>
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 * <p>
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */
public class _212WordSearchII {
    public static void main(String[] args) {
        _212WordSearchII test = new _212WordSearchII();
        char[][] board = new char[][]{{'a', 'a'}};
        String[] words = new String[]{"aaa"};
        System.out.println(test.findWords(board, words));

    }

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        if (words == null || board == null) {
            return new LinkedList<>(result);
        }
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (String word : words) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (dfs(board, visited, i, j, word.toCharArray(), 0)) {
                        result.add(word);
                    }
                }
            }
        }
        return new LinkedList<>(result);
    }

    @SuppressWarnings("Duplicates")
    public static boolean dfs(char[][] board, boolean[][] visited,
                              int i, int j, char[] target, int pos) {
        if (pos == target.length) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (visited[i][j]) {
            return false;
        }
        if (board[i][j] != target[pos]) {
            return false;
        }
        visited[i][j] = true;
        boolean exist = dfs(board, visited, i - 1, j, target, pos + 1)
                || dfs(board, visited, i + 1, j, target, pos + 1)
                || dfs(board, visited, i, j - 1, target, pos + 1)
                || dfs(board, visited, i, j + 1, target, pos + 1);
        visited[i][j] = false;
        return exist;
    }
}

