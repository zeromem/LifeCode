package org.zeromem.lifecode.algorithmaction;

import java.util.*;

/**
 * @author zeromem
 * @date 2017/12/12
 */
public class SingleSourceShortestPath {
    public static void main(String[] args) {
        SingleSourceShortestPath test = new SingleSourceShortestPath();
        int[][] edges = new int[][]{
                {1, 2, 7}, {1, 3, 9}, {1, 6, 14},
                {2, 3, 10}, {2, 4, 15},
                {3, 4, 11}, {3, 6, 2},
                {4, 5, 6}, {5, 6, 9},
                {7, 8, 5}, {7, 9, 5}
        };
        test.longestPath(edges, 9, 1);
    }

    /**
     * 节点编号1~N，edges每个元素指定一条有向边[source, target, weight]，求解从节点K出发，最长的路径权重和，若有节点不可达，返回-1.
     *
     * @param edges
     * @param N
     * @param K
     * @return
     */
    public int longestPath(int[][] edges, int N, int K) {
        HashMap<Integer, Integer> S = new HashMap<>();
        int[] memo = new int[N + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[K] = 0;

        // source -> [{}, {target, weight}, ...]
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int[] edge : edges) {
            Integer source = edge[0];
            if (!map.containsKey(source)) {
                map.put(source, new LinkedList<>());
            }
            map.get(source).add(new int[]{edge[1], edge[2]});
        }

        S.put(K, 0);
        Integer node = K;
        for (int i = 0; i < N - 1; i++) {
            int partOpt = S.get(node);
            List<int[]> neibors = map.get(node);
            if (neibors != null) {
                for (int[] neibor : neibors) {
                    if (partOpt + neibor[1] < memo[neibor[0]]) {
                        memo[neibor[0]] = partOpt + neibor[1];
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            int pos = 0;
            for (int j = 1; j <= N; j++) {
                if (!S.containsKey(j) && memo[j] < min) {
                    min = memo[j];
                    pos = j;
                }
            }
            if (pos != 0) {
                S.put(pos, memo[pos]);
                node = pos;
            }
        }
        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (memo[i] > max) {
                max = memo[i];
            }
        }
        return max == Integer.MAX_VALUE ? -1 : max;
    }
}
