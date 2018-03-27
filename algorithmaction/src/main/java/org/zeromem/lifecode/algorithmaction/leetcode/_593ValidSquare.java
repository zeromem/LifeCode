package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;

/**
 * @author zeromem@qq.com
 * @date 2018/3/26
 * <p>
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
 * <p>
 * The coordinate (x,y) of a point is represented by an integer array with two integers.
 * <p>
 * Example:
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: True
 * Note:
 * <p>
 * All the input integers are in the range [-10000, 10000].
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 * Input points have no order.
 */
public class _593ValidSquare {
    public static void main(String[] args) {
        _593ValidSquare test = new _593ValidSquare();
        int[] p1 = new int[]{0, 0};
        int[] p2 = new int[]{1, 1};
        int[] p3 = new int[]{1, 0};
        int[] p4 = new int[]{0, 1};
        System.out.println(test.validSquare(p1, p2, p3, p4));

    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        double d12 = distPow(p1, p2);
        double d13 = distPow(p1, p3);
        double d14 = distPow(p1, p4);
        double d23 = distPow(p2, p3);
        double d24 = distPow(p2, p4);
        double d34 = distPow(p3, p4);

        double[] list = new double[]{d12, d13, d14, d23, d24, d34};
        Arrays.sort(list);
        return Double.compare(list[0], 0d) != 0
                && Double.compare(list[0], list[1]) == 0
                && Double.compare(list[0], list[2]) == 0
                && Double.compare(list[0], list[3]) == 0
                && Double.compare(list[0] * 2, list[4]) == 0
                && Double.compare(list[4], list[5]) == 0;
    }

    public double distPow(int[] p1, int[] p2) {
        return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
    }
}
