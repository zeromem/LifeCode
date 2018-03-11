package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/7
 */
public class _463IslandPerimeter {
    public static void main(String[] args) {
        _463IslandPerimeter test = new _463IslandPerimeter();

    }

    public int islandPerimeter(int[][] grid) {
        int numCells = 0, numJoint = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    numCells++;
                    if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                        numJoint++;
                    }
                    if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
                        numJoint++;
                    }
                }
            }
        }

        return ((numCells << 1) - numJoint) << 1;
    }
}
