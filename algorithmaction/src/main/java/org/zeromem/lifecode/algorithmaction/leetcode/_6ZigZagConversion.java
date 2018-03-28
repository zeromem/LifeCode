package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem@qq.com
 * @date 2018/3/27
 * 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：（下面这样的形状）
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后按逐行顺序依次排列："PAHNAPLSIIGYIR"
 * <p>
 * <p>
 * <p>
 * 实现一个将字符串进行指定行数的转换的函数:
 * <p>
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) 应当返回 "PAHNAPLSIIGYIR" 。
 */
public class _6ZigZagConversion {
    public static void main(String[] args) {
        _6ZigZagConversion test = new _6ZigZagConversion();
        String s = "PAYPALISHIRING";
        s = "AB";
        System.out.println(test.convert(s, 1));
    }

    public String convert(String s, int numRows) {
        if (s == null) {
            return null;
        }
        if (s.length() <= numRows || numRows == 1) {
            return s;
        }
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < sbs.length; i++) {
            sbs[i] = new StringBuilder();
        }

        int direct = 1, curRow = 0, i = 0, len = s.length();
        while (i < len) {
            sbs[curRow].append(s.charAt(i));
            i++;
            curRow += direct;
            if (curRow == numRows - 1) {
                direct = -1;
            } else if (curRow == 0) {
                direct = 1;
            }
        }

        for (int j = 1; j < numRows; j++) {
            sbs[0].append(sbs[j]);
        }
        return sbs[0].toString();
    }
}
