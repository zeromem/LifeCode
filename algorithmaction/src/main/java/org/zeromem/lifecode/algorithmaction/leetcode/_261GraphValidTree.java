package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/5
 */
public class _261GraphValidTree {
    public static void main(String[] args) {
        _261GraphValidTree test = new _261GraphValidTree();
        int n = 5;
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 4}, {0, 3}};
        boolean res = test.validTree(n, edges);
        System.out.println(res);

        n = 5;
        edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 4}};
        res = test.validTree(n, edges);
        System.out.println(res);
    }

    public boolean validTree(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge : edges) {
            boolean sameGroup = uf.union(edge[0], edge[1]);
            if (sameGroup) {
                return false;
            }
        }
        return uf.groupCount == 1;
    }
}

class UF {
    int groupCount;
    int[] parent;

    public UF(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        groupCount = n;
    }

    public int find(int x) {
        int p = parent[x];
        while (parent[p] != p) {
            p = parent[p];
        }
        parent[x] = p;  //!!!
        return p;
    }

    /**
     *
     * @param x
     * @param y
     * @return return true if x and y are in the same group before.
     */
    public boolean union(int x, int y) {
        if (y < x) {
            return union(y, x);
        }
        int px = find(x);
        int py = find(y);

        if (px == py) {
            return true;
        }

        groupCount--;
        parent[py] = px;
        return false;
    }
}
