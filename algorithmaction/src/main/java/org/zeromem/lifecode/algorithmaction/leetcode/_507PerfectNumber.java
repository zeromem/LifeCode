package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/12/8
 */
public class _507PerfectNumber {
    public static void main(String[] args) {
        _507PerfectNumber test = new _507PerfectNumber();
        System.out.println(test.checkPerfectNumber(99999995));
    }

    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int acc = 1;
        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                acc += i + num / i;
            }
        }
        return acc == num;
    }
}
