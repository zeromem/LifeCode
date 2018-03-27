package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem@qq.com
 * @date 2018/3/12
 *
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 */
public class _11ContainerWithMostWater {
    public static void main(String[] args) {
        _11ContainerWithMostWater test = new _11ContainerWithMostWater();
        int[] height = new int[]{1, 1};
        System.out.println(test.maxArea(height));
    }

    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int i = 0, j = height.length - 1;
        int maxArea = 0;
        int area;
        while (i < j) {
            area = j - i;
            if (height[i] <= height[j]) {
                area *= height[i++];
            } else {
                area *= height[j--];
            }
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }
}
