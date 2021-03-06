package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Map;
import java.util.Properties;

/**
 * @author zeromem
 * @date 2018/3/7
 * Example 1:
 * <p>
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * <p>
 * Example 2:
 * <p>
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 */
public class _200NumberofIslands {
    public static void main(String[] args) {

    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // is a new island.
                if (bfs(grid, i, j, visited)) {
                    result++;
                }
            }
        }
        return result;
    }

    public static boolean bfs(char[][] grid, int i, int j, boolean[][] visited) {
        if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && !visited[i][j] && grid[i][j] == '1') {
            visited[i][j] = true;
            bfs(grid, i + 1, j, visited);
            bfs(grid, i - 1, j, visited);
            bfs(grid, i, j + 1, visited);
            bfs(grid, i, j - 1, visited);
            return true;
        }
        return false;
    }
}
