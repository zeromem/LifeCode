package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zeromem
 * @date 2018/3/7
 */
public class _695MaxAreaofIsland {
    public static void main(String[] args) {
        _695MaxAreaofIsland test = new _695MaxAreaofIsland();
        int[][] grid = new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        int result = test.maxAreaOfIsland(grid);
        System.out.println(result);
    }

    public int maxAreaOfIsland(int[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                visited[i][j] = -1;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                dfs(grid, i, j, visited, i * grid[0].length + j);
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                int id = visited[i][j];
                Integer pre = map.putIfAbsent(id, 1);
                if (pre != null) {
                    map.put(id, pre + 1);
                }
            }
        }

        map.remove(-1);
        int max = 0;
        for (Integer area : map.values()) {
            if (area > max) {
                max = area;
            }
        }
        return max;
    }

    public static void dfs(int[][] grid, int i, int j, int[][] visited, int id) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && visited[i][j] == -1 && grid[i][j] == 1) {
            visited[i][j] = id;
            dfs(grid, i - 1, j, visited, id);
            dfs(grid, i + 1, j, visited, id);
            dfs(grid, i, j - 1, visited, id);
            dfs(grid, i, j + 1, visited, id);
        }
    }
}
