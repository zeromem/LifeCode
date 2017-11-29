package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/11/29
 */
public class _461HammingDistance {
    public static void main(String[] args) {
        _461HammingDistance test = new _461HammingDistance();
        System.out.println(test.hammingDistance(1, 4));
    }

    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        return Integer.bitCount(xor);
    }
}
