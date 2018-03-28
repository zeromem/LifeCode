package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem@qq.com
 * @date 2018/3/26
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.
 * <p>
 * Note:
 * <p>
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class _43MultiplyStrings {
    public static void main(String[] args) {
        _43MultiplyStrings test = new _43MultiplyStrings();
        String num1 = "123";
        String num2 = "4569";
        String result = test.multiply(num1, num2);
        System.out.println(result);
    }

    public String multiply(String num1, String num2) {
        if (num1 == null && num2 == null) {
            return null;
        }
        if (num1 == null) {
            return num2;
        }
        if (num2 == null) {
            return num1;
        }

        char[] cs1 = new StringBuilder(num1).reverse().toString().toCharArray();
        char[] cs2 = new StringBuilder(num2).reverse().toString().toCharArray();

        char[][] result = new char[num2.length()][];
        for (int i = 0; i < cs2.length; i++) {
            result[i] = charMulString(cs2[i], cs1, i);
        }
        char[] acc = result[0];

        for (int i = 1; i < result.length; i++) {
            acc = add(acc, result[i]);
        }

        StringBuilder sb = new StringBuilder(new String(acc));
        int i;
        for (i = acc.length - 1; i >= 0; i--) {
            if (acc[i] != '0') {
                break;
            }
        }

        return new StringBuilder(new String(acc, 0, i + 1)).reverse().toString();
    }

    char[] add(char[] cs1, char[] cs2) {
        int n1 = cs1.length, n2 = cs2.length;
        char[] result = new char[1 + (n1 > n2 ? n1 : n2)];
        int carry = 0;
        int i;
        for (i = 0; i < result.length - 1; i++) {
            int value = carry;
            if (i < n1) {
                value += cs1[i] - '0';
            }
            if (i < n2) {
                value += cs2[i] - '0';
            }

            result[i] = (char) ('0' + value % 10);
            carry = value / 10;
        }
        if (carry != 0) {
            result[i] = (char) ('0' + carry);
        } else {
            result[i] = '0';
        }
        return result;
    }

    char[] charMulString(char c, char[] cs, int offset) {
        char[] result = new char[offset + cs.length + 1];
        int i;
        for (i = 0; i < offset; i++) {
            result[i] = '0';
        }
        int carry = 0;
        for (; i < result.length - 1; i++) {
            result[i] = (char) ('0' + (carry + (c - '0') * (cs[i - offset] - '0') % 10));
            carry = (c - '0') * (cs[i - offset] - '0') / 10;
        }
        if (carry != 0) {
            result[i] = (char) ('0' + carry);
        } else {
            result[i] = '0';
        }
        return result;
    }
}
