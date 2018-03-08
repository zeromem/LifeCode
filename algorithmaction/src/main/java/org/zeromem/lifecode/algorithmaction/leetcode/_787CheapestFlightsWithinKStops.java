package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by zeromem on 2018/3/8.
 */
public class _787CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        _787CheapestFlightsWithinKStops test = new _787CheapestFlightsWithinKStops();
        int n = 3;
        int[][] flights = new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0;
        int dst = 2;
        int k = 1;
        int result = test.findCheapestPrice(n, flights, src, dst, k);
        System.out.println(result);
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }

        // int[]{destination, stops, weight}
        PriorityQueue<int[]> queue = new PriorityQueue<>((f1, f2) -> f1[2] - f2[2]);
        queue.add(new int[]{src, k + 1, 0});
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int node = pair[0];
            int stops = pair[1];
            int weight = pair[2];
            if (node == dst) {
                return weight;
            }
            if (stops > 0) {
                for (int next = 0; next < n; next++) {
                    if (graph[node][next] != 0) {
                        queue.add(new int[]{next, stops - 1, graph[node][next] + weight});
                    }
                }
            }
        }

        return -1;
    }
}
