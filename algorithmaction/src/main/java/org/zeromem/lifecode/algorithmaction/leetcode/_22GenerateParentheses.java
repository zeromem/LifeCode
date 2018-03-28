package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zeromem
 * @date 2018/3/20
 */
public class _22GenerateParentheses {
    public static void main(String[] args) {
        _22GenerateParentheses test = new _22GenerateParentheses();
        System.out.println(test.generateParenthesis(4));
    }

    public List<String> generateParenthesis(int n) {
        // TODO: 2018/3/24 算法还有问题
        List<String> result = new LinkedList<>();
        if (n == 0) {
            return result;
        }
        if (n == 1) {
            result.add("()");
            return result;
        }
        List<String> partial = generateParenthesis(n - 1);
        for (String s : partial) {
            result.add("(" + s + ")");
            result.add("()" + s);
            if (!("()" + s).equals(s + "()")) {
                result.add(s + "()");
            }
        }
        return result;
    }

}
