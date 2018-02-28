package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/12/12
 * 并查集
 */
public class UnionFind {
    private int groupCount = 0;
    private int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        groupCount = n;
    }

    public int find(int x) {
        int r = x;
        while (parent[r] != r) {
            r = parent[r];
        }
        parent[x] = r;
        return r;
    }

    public void union(int x, int y) {
        if (y < x) {
            union(y, x);
        } else {
            int px = find(x);
            int py = find(y);
            if (px == py) {
                return;
            } else {
                parent[py] = px;
                groupCount--;
            }
        }
    }

    public boolean isConnected(int x, int y) {
        int px = find(x);
        int py = find(y);
        return px == py;
    }

    public int getCount() {
        return groupCount;
    }
}
