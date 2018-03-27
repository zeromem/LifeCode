package org.zeromem.lifecode.algorithmaction;

import java.util.*;

/**
 * @author zeromem@qq.com
 * @date 2018/3/26
 * 输入abc，输出
 * abc
 * acb
 * bac
 * cab
 * bca
 * cba
 * 即f(n) = n!
 */
public class AllLetterCombination {
    public static void main(String[] args) {
        AllLetterCombination test = new AllLetterCombination();
        String s = "abad";
        List<String> result = test.allCombine(s);
        System.out.println(result.size());
        for (String s1 : result) {
            System.out.println(s1);
        }
    }

    public List<String> allCombine(String s) {
        List<String> result = new LinkedList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        if (s.length() == 1) {
            result.add(s);
            return result;
        }

        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            StringBuilder sb = new StringBuilder();
            if (i - 1 >= 0) {
                sb.append(cs, 0, i);
            }
            if (i + 1 < cs.length) {
                sb.append(cs, i + 1, cs.length - i - 1);
            }
            String other = sb.toString();
            List<String> partResultList = allCombine(other);
            for (String part : partResultList) {
                result.add(c + part);
            }
        }

        return result;
    }
}
