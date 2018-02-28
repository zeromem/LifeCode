package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/12/28
 * <p>
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * <p>
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 * <p>
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 * <p>
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class _402RemoveKDigits {
    public static void main(String[] args) {
        _402RemoveKDigits test = new _402RemoveKDigits();
        String num = "10";
        String res = test.removeKdigits(num, 3);
        System.out.println(res);
    }

    public String removeKdigits(String num, int k) {
        int n = num.length();
        int len = n - k;
        if (len <= 0) {
            return "0";
        }
        char[] stack = new char[n];
        stack[0] = num.charAt(0);
        int top = 0;
        for (int i = 1; i < n; i++) {
            char c = num.charAt(i);
            while (top >= 0 && k > 0 && c < stack[top]) {
                top--;
                k--;
            }
            stack[++top] = (char) c;
        }
        int min = Integer.valueOf(new String(stack, 0, len), 10);
        return String.valueOf(min);
    }
}
