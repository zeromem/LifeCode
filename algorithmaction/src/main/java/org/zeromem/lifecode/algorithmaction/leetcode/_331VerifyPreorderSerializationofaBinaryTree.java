package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zeromem
 * @date 2018/3/16
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.
 * <p>
 * _9_
 * /   \
 * 3     2
 * / \   / \
 * 4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
 * <p>
 * Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.
 * <p>
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 * <p>
 * You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
 * <p>
 * Example 1:
 * "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Return true
 * <p>
 * Example 2:
 * "1,#"
 * Return false
 * <p>
 * Example 3:
 * "9,#,#,1"
 * Return false
 * <p>
 * 两个#抵消一个数字，且生成一个#。
 */
public class _331VerifyPreorderSerializationofaBinaryTree {
    public static void main(String[] args) {
        _331VerifyPreorderSerializationofaBinaryTree test = new _331VerifyPreorderSerializationofaBinaryTree();
        String preorder = "9,#,92,#,#";
        System.out.println(test.isValidSerialization(preorder));

    }

    public boolean isValidSerialization(String preorder) {
        if (preorder == null) {
            return false;
        }
        if (preorder.equals("#")) {
            return true;
        }
        if (preorder.charAt(0) == '#') {
            return false;
        }
        String[] arr = preorder.split(",");
        if ((arr.length & 0x1) == 0) {
            return false;
        }

        // use deque as stack.
        Deque<String> stack = new LinkedList<>();
        for (String c : arr) {
            if (!"#".equals(c)) {
                stack.addLast(c);
            } else {
                if (!stack.getLast().equals("#")) {
                    stack.addLast(c);
                } else {
                    while (!stack.isEmpty() && stack.getLast().equals("#")) {
                        stack.removeLast();
                        if (stack.isEmpty()) {
                            return false;
                        }
                        stack.removeLast();
                    }
                    stack.addLast("#");
                }
            }
        }
        return stack.size() == 1 && stack.getLast().equals("#");
    }
}
