package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.*;

/**
 * @author zeromem
 * @date 2018/3/1
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * For example:
 * <p>
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * <p>
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 */
public class _207CourseSchedule {
    public static void main(String[] args) {
        _207CourseSchedule test = new _207CourseSchedule();
        int numCourses = 3;
        int[][] prerequisites = new int[][]{{1, 0}, {2, 0}};
        boolean res = test.canFinish(numCourses, prerequisites);
        System.out.println(res);

    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> preCount = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            preCount.put(i, 0);
        }
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            graph.putIfAbsent(prerequisite[0], new HashSet<>());
            graph.get(prerequisite[0]).add(prerequisite[1]);
            Integer count = preCount.get(prerequisite[1]);
            preCount.put(prerequisite[1], count + 1);
        }

        while (!graph.isEmpty()) {
            Integer cour = -1;
            for (Map.Entry<Integer, Integer> entry : preCount.entrySet()) {
                if (entry.getValue() == 0) {
                    cour = entry.getKey();
                    preCount.remove(cour);
                    break;
                }
            }
            if (cour == -1 && !graph.isEmpty()) {
                return false;
            }
            Set<Integer> children = graph.get(cour);
            graph.remove(cour);
            if (children != null) {
                for (Integer child : children) {
                    int count = preCount.get(child);
                    preCount.put(child, count - 1);
                }
            }
        }
        return true;
    }

}
