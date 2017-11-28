package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * Created by zeromem on 2017/5/3.
 */
public class _404LeftSum {
    public static void main(String[] args) {
        _404LeftSum test = new _404LeftSum();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(5);
        System.out.println(test.sumOfLeftLeaves(root));

    }


    public static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }


    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) {
            return sumOfLeftLeaves(root.right);
        }

        if (isLeaf(root.left)) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }

        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
}
