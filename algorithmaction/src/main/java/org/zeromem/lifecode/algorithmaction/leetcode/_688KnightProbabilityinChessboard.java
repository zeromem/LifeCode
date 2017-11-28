package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/11/9
 */
public class _688KnightProbabilityinChessboard {
    public static double[][][] memo = new double[100][25][25];
    public static void main(String[] args) {
        _688KnightProbabilityinChessboard test = new _688KnightProbabilityinChessboard();
        System.out.println(test.knightProbability(3, 2, 0, 0));
        System.out.println(System.currentTimeMillis());
        System.out.println(test.knightProbability(8, 3, 6, 4));
        System.out.println(System.currentTimeMillis());
    }

    public double knightProbability(int n, int k, int r, int c) {
        if (r < 0 || c < 0 || r > n - 1 || c > n - 1) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }

        double prob = 0;

        prob += knightProbability(n, k - 1, r - 1, c - 2) / 8;
        prob += knightProbability(n, k - 1, r - 1, c + 2) / 8;
        prob += knightProbability(n, k - 1, r + 1, c - 2) / 8;
        prob += knightProbability(n, k - 1, r + 1, c + 2) / 8;
        prob += knightProbability(n, k - 1, r - 2, c - 1) / 8;
        prob += knightProbability(n, k - 1, r - 2, c + 1) / 8;
        prob += knightProbability(n, k - 1, r + 2, c - 1) / 8;
        prob += knightProbability(n, k - 1, r + 2, c + 1) / 8;
        return prob;
    }
}
