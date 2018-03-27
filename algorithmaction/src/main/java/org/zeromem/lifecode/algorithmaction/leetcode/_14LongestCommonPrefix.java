package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem@qq.com
 * @date 2018/3/27
 */
public class _14LongestCommonPrefix {
    public static void main(String[] args) {
        _14LongestCommonPrefix test = new _14LongestCommonPrefix();
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            return null;
        }
        if (strs.length == 0) {
            return "";
        }
        for (String str : strs) {
            if (str == null) {
                return null;
            }
            if (str.length() == 0) {
                return "";
            }
        }
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
