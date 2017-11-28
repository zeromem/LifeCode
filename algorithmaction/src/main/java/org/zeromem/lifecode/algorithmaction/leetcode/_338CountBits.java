package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * Created by zeromem on 2016/11/7.
 */
public class _338CountBits {
    public static void main(String[] args) {
        _338CountBits test = new _338CountBits();
        for (int i : test.countBits(5)) {
            System.out.println(i);
        }

    }

    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        if (num == 0) return res;
        res[1] = 1;
        if (num == 1) return res;

        int pos = 2;
        int step = 1;
        while (true) {
            step <<= 1;
            for (int i = 0; i < step; i++) {
                res[pos] = res[pos - step] + 1;
                pos++;
                if (pos > num) {
                    return res;
                }
            }
        }
    }

    /**
     * 偶数x的set bit数量和x/2的set bit数量相同
     * 奇数x+1的set bit数量比x的setbit数量多1
     * @param num
     * @return
     */
    public int[] countBits2(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i += 2) {
            res[i] = res[i >> 1];
            res[i + 1] = res[i] + 1;
        }
        res[num] = res[num >> 1] + (num&1);
        return res;
    }
}
