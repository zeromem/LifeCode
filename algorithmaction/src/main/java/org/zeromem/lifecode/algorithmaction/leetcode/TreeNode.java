package org.zeromem.lifecode.algorithmaction.leetcode;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zeromem
 * @date 2017/11/1
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(3);
        root.left.left.right = new TreeNode(1);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(12);
        root.right.left.left = new TreeNode(5);
        root.right.left.right = new TreeNode(9);
        root.right.left.right.left = new TreeNode(7);

        levelTravel(root);
    }

    public static void levelTravel(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return;
        }
        queue.add(root);
        TreeNode nextLevelEnd = root;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            if (node == nextLevelEnd) {
                System.out.println();
                nextLevelEnd = node.right;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}
