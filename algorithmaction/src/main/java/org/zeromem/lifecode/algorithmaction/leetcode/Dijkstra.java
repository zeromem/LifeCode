package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.*;

/**
 * Created by zeromem on 2018/3/7.
 */
public class Dijkstra {
    public static void main(String[] args) {

    }

    public Map<Integer, Integer> search(int[][] matrix, int src) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        List<int[]> result = new LinkedList<>();
        queue.add(new Edge(src, src, 0));
        Map<Integer, Integer> map = new HashMap<>();
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            if (map.containsKey(edge.targetId)) {
                continue;
            }
            map.put(edge.targetId, map.get(edge.sourceId) + edge.weight);

            for (int nextId = 0; nextId < matrix[edge.targetId].length; nextId++) {
                if (matrix[edge.targetId][nextId] > 0) {
                    queue.add(new Edge(edge.targetId, nextId, matrix[edge.targetId][nextId]));
                }
            }
        }
        return map;
    }
}

class Edge {
    Integer sourceId;
    Integer targetId;
    Integer weight;

    public Edge(Integer sourceId, Integer targetId, Integer weight) {
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.weight = weight;
    }

    public int[] toArray() {
        return new int[]{sourceId, targetId, weight};
    }
}
