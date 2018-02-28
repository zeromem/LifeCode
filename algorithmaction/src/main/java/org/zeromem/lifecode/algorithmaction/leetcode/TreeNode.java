package org.zeromem.lifecode.algorithmaction.leetcode;


import java.util.LinkedList;
import java.util.List;
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
        List<TreeNode> nodes = levelOrder(root);
        for (TreeNode node : nodes) {
            System.out.print(node.val + " ");
        }
        System.out.println();


        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(6);
        levelTravel(root2);
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
        System.out.println();
    }

    public static List<TreeNode> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<TreeNode> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node); // queue中的数据需要出队列，用result来保存所有数据
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return result;
    }
}
