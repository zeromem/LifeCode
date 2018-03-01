package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zeromem
 * @date 2018/3/1
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * <p>
 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
 * <p>
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 * <p>
 * Example 1
 * <p>
 * Input 1: a maze represented by a 2D array
 * <p>
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * <p>
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 * <p>
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 *
 * 小球一直滚动，遇到墙才会停
 */
public class _490TheMaze {
    public static void main(String[] args) {
        _490TheMaze test = new _490TheMaze();
        int[][] maze = new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = new int[]{0, 4};
        int[] destination = new int[]{4, 4};
        System.out.println(test.hasPath(maze, start, destination));
    }

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        queue.add(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == destination[0] && cur[1] == destination[1]) {
                return true;
            }
            for (int[] dir : dirs) {
                int i = cur[0] + dir[0];
                int j = cur[1] + dir[1];

                while (i >= 0 && i < maze.length && j >= 0 && j < maze[0].length && maze[i][j] == 0) {
                    // 没有遇到障碍，一直沿当前方向前进
                    i += dir[0];
                    j += dir[1];
                }

                // 如果当前停止的位置之前没访问过
                i -= dir[0];
                j -= dir[1];
                if (!visited[i][j]) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        return false;
    }
}
