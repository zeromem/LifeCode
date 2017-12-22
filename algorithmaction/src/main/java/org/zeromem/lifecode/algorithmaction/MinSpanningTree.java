package org.zeromem.lifecode.algorithmaction;

import org.zeromem.lifecode.algorithmaction.leetcode.UnionFind;

import java.util.*;

/**
 * @author zeromem
 * @date 2017/12/19
 * 最小生成树
 */
public class MinSpanningTree {
    public static void main(String[] args) {
        MinSpanningTree test = new MinSpanningTree();
        int[][] graph = new int[][]{{1, 2, 7}, {1, 4, 5},
                {2, 3, 8}, {2, 4, 9}, {2, 5, 7},
                {3, 5, 5},
                {4, 5, 15}, {4, 6, 6},
                {5, 6, 8}, {5, 7, 9},
                {6, 7, 11}
        };
        int[][] tree = test.kruskal(graph);
        for (int[] edge : tree) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     *
     * @param graph [[source, target, weight], [], ...]
     */
    public int[][] kruskal(int[][] graph) {
        List<int[]> tree = new ArrayList<>();
        List<int[]> g = Arrays.asList(graph);
        UnionFind uf = new UnionFind(g.size());
        g.sort(Comparator.comparingInt(o -> o[2]));

        for (int[] edge : g) {
            System.out.print(Arrays.toString(edge));
            if (uf.find(edge[0] - 1) != uf.find(edge[1] - 1)) {
                System.out.println(" yes.");
                tree.add(edge);
                uf.union(edge[0] - 1, edge[1] - 1);
            } else {
                System.out.println(" no.");
            }
        }
        tree.sort(Comparator.comparingInt(o -> o[0]));
        return tree.toArray(new int[][]{});
    }

}
