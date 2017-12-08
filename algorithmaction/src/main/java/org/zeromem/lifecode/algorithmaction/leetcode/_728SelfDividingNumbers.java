package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zeromem
 * @date 2017/12/5
 * <p>
 * A self-dividing number is a number that is divisible by every digit it contains.
 * <p>
 * For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
 * <p>
 * Also, a self-dividing number is not allowed to contain the digit zero.
 * <p>
 * Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.
 * <p>
 * Example 1:
 * Input:
 * left = 1, right = 22
 * Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * Note:
 * <p>
 * The boundaries of each input argument are 1 <= left <= right <= 10000.
 */
public class _728SelfDividingNumbers {
    public static void main(String[] args) {
        _728SelfDividingNumbers test = new _728SelfDividingNumbers();
        System.out.println(test.selfDividingNumbers(1, 22));
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new LinkedList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDivid(i)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean isSelfDivid(int a) {
        int r = a;
        while (r > 0) {
            int x = r % 10;
            if (x == 0 || a % x != 0) {
                return false;
            }
            r = r / 10;
        }
        return true;
    }
}
