package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zeromem on 2016/11/8.
 */
public class _68TextJustification {
    public static void main(String[] args) {
        _68TextJustification test = new _68TextJustification();
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> res = test.fullJustify(words, 16);
        for (String re : res) {
            System.out.println(re);
        }

    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new LinkedList<>();
        int curWidth = 0;
        StringBuilder sb = new StringBuilder();
        List<String> tmp = new LinkedList<>();
        for (String word : words) {
            int len = word.length();
            if (curWidth + len > maxWidth && curWidth != 0) {
                if (tmp.size() == 1) {
                    sb.append(tmp.get(0));
                } else {
                    int leastSpace = (maxWidth - curWidth + tmp.size()) / (tmp.size() - 1);
                    int leftMoreOne = (maxWidth - curWidth + tmp.size()) % (tmp.size() - 1);
                    int i;
                    for (i = 0; i < leftMoreOne; i++) {
                        sb.append(tmp.get(i));
                        for (int j = 0; j < leastSpace + 1; j++) {
                            sb.append(" ");
                        }
                    }
                    for (; i < tmp.size(); i++) {
                        sb.append(tmp.get(i));
                        for (int j = 0; j < leastSpace; j++) {
                            sb.append(" ");
                        }
                    }
                }
                res.add(sb.toString());
                sb = new StringBuilder();
                tmp = new LinkedList<>();
                curWidth = 0;
                tmp.add(word);
                curWidth += len + 1;

            } else {
                tmp.add(word);
                curWidth += len + 1;
            }
        }
        for (String word : tmp) {
            sb.append(word);
        }
        res.add(sb.toString());
        return res;
    }
}
