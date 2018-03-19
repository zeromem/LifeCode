package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zeromem
 * @date 2018/3/16
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
 * <p>
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.
 * <p>
 * Example:
 * Input: [[1,2], [3], [3], []]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Note:
 * <p>
 * The number of nodes in the graph will be in the range [2, 15].
 * You can print different paths in any order, but you should keep the order of nodes inside one path.
 */
public class _797AllPathsFromSourcetoTarget {
    public static void main(String[] args) {

    }

    /**
     * graph是一个有向无环图
     *
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new LinkedList<>();
        if (graph == null) {
            return result;
        }
        int N = graph.length;
        LinkedList<Integer> init = new LinkedList<>();
        init.add(0);
        Queue<LinkedList<Integer>> queue = new LinkedList<>();
        queue.add(init);
        while (!queue.isEmpty()) {
            LinkedList<Integer> path = queue.poll();
            if (path.getLast() == N - 1) {
                result.add(path);
            } else {
                queue.addAll(bfs(graph, path));
            }
        }
        return result;
    }

    public static List<LinkedList<Integer>> bfs(int[][] graph, LinkedList<Integer> part) {
        List<LinkedList<Integer>> result = new LinkedList<>();
        int last = part.getLast();
        for (int next : graph[last]) {
            LinkedList<Integer> list = new LinkedList<>(part);
            list.add(next);
            result.add(list);
        }
        return result;
    }
}
