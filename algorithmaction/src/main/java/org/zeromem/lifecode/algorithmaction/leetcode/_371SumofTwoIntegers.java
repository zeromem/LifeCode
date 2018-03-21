package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/20
 */
public class _371SumofTwoIntegers {
    public static void main(String[] args) {

    }

    public int getSum(int a, int b) {
        if (b == 0) {
            return a;
        }
        int sum = 0;
        while (b != 0) {
            sum = a ^ b;
            // b is carry.
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }
}
