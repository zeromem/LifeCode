package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/2/27
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
 * <p>
 * Example 1:
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * Example 2:
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 */
public class _670MaximumSwap {
    public static void main(String[] args) {
        _670MaximumSwap test = new _670MaximumSwap();
        int a = 9973;
        int res = test.maximumSwap(a);
        System.out.println(res);
    }

    public int maximumSwap(int num) {
        int max = num;
        char[] chars = String.valueOf(num).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[j] > chars[i]) {
                    swap(chars, i, j);
                    Integer val = Integer.valueOf(String.valueOf(chars));
                    if (val > max) {
                        max = val;
                    }
                    swap(chars, i, j);
                }
            }
        }
        return max;
    }

    public static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
