package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Map;
import java.util.Properties;

/**
 * @author zeromem
 * @date 2018/3/7
 */
public class _200NumberofIslands {
    public static void main(String[] args) {
        Map<String, String> env = System.getenv();
        Properties properties = System.getProperties();
        System.out.println(env);
        System.out.println(properties);
        System.out.println(env.get("hello"));
        System.out.println(properties.getProperty("hello"));

    }

    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (dfs(grid, i, j, visited)) {
                    result++;
                }
            }
        }
        return result;
    }

    public static boolean dfs(char[][] grid, int i, int j, boolean[][] visited) {
        if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && !visited[i][j] && grid[i][j] == '1') {
            visited[i][j] = true;
            dfs(grid, i + 1, j, visited);
            dfs(grid, i - 1, j, visited);
            dfs(grid, i, j + 1, visited);
            dfs(grid, i, j - 1, visited);
            return true;

        }
        return false;
    }
}
