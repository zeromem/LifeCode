package org.zeromem.lifecode.algorithmaction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zeromem
 * @date 2017/12/12
 */
public class SingleSourceShortestPath {
    public static void main(String[] args) {

    }

    /**
     * 节点编号1~N，edges每个元素指定一条有向边[source, target, weight]，求解从节点K出发，最长的路径权重和，若有节点不可达，返回-1.
     * @param edges
     * @param N
     * @param K
     * @return
     */
    public int longestPath(int[][] edges, int N, int K) {
        HashMap<Integer, Integer> S = new HashMap<>();
        HashSet<Integer> U = new HashSet<>();

        // source -> [{}, {target, weight}, ...]
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int[] edge : edges) {
            Integer source = edge[0];
            Integer target = edge[1];
            Integer weight = edge[2];
            map.getOrDefault(source, new LinkedList<>()).add(new int[]{target, weight});
            U.add(edge[0]);
        }
        U.remove(K);
        S.put(K, 0);
        Integer newNode = K;
        whi





        return -1;
    }
}
