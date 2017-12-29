package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/12/28
 * <p>
 * Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.
 * <p>
 * (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)
 * <p>
 * Example 1:
 * Input: N = 10
 * Output: 9
 * Example 2:
 * Input: N = 1234
 * Output: 1234
 * Example 3:
 * Input: N = 332
 * Output: 299
 * Note: N is an integer in the range [0, 10^9].
 */
public class _738MonotoneIncreasingDigits {
    public static void main(String[] args) {
        _738MonotoneIncreasingDigits test = new _738MonotoneIncreasingDigits();
        int N = 120;
        System.out.println(test.monotoneIncreasingDigits(N));
    }

    public int monotoneIncreasingDigits(int N) {
        String s = String.valueOf(N);
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] < chars[i - 1]) {
                int j;
                for (j = i - 2; j >= 0; j--) {
                    if (chars[j] != chars[i - 1]) {
                        break;
                    }
                }
                chars[j + 1]--;
                for (int k = j + 2; k < chars.length; k++) {
                    chars[k] = '9';
                }
                return Integer.valueOf(String.valueOf(chars));
            }
        }
        return N;
    }

}
