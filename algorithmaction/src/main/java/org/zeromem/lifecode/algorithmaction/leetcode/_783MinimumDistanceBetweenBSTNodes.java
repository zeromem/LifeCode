package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zeromem
 * @date 2018/2/27
 */
public class _783MinimumDistanceBetweenBSTNodes {
    public static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
        _783MinimumDistanceBetweenBSTNodes test = new _783MinimumDistanceBetweenBSTNodes();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(48);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(49);
        int res = test.minDiffInBST(root);
        System.out.println(res);
    }

    public int minDiffInBST(TreeNode root) {
        list = new ArrayList<>();
        infixScan(root);
        int size = list.size();
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < size; i++) {
            int cur = list.get(i);
            int pre = list.get(i - 1);
            if (cur - pre < min) {
                min = cur - pre;
            }
        }
        return min;
    }

    public static void infixScan(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            infixScan(root.left);
        }
        list.add(root.val);
        if (root.right != null) {
            infixScan(root.right);
        }
    }
}
