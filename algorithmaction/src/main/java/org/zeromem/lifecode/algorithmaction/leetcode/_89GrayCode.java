package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zeromem
 * @date 2018/3/13
 */
public class _89GrayCode {
    public static void main(String[] args) {

    }

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>(n);
        long ceil = 1 << n;
        for (int i = 0; i < ceil; i++) {
            result.add(i ^ (i >> 1));
        }
        return result;
    }
}
