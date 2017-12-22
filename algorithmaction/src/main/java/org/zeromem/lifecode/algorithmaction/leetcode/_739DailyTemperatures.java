package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2017/12/22
 * <p>
 * Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * <p>
 * For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 * <p>
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class _739DailyTemperatures {
    public static void main(String[] args) {
        _739DailyTemperatures test = new _739DailyTemperatures();
        int[] temp = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(test.dailyTemperatures(temp)));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        int s = 0;
        for (int i = 0; i < n; i++) {
            for (int j = s; j < i; j++) {
                if (temperatures[j] > temperatures[s]) {
                    res[s] = j - s;
                    s++;
                    System.out.println(Arrays.toString(res));
                }
            }
        }
        return res;
    }
}
