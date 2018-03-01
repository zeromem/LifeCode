package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zeromem
 * @date 2018/3/1
 */
public class _107BinaryTreeLevelOrderTraversalII {
    public static void main(String[] args) {
        _107BinaryTreeLevelOrderTraversalII test = new _107BinaryTreeLevelOrderTraversalII();
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

        List<List<Integer>> result = test.levelOrderBottom(root);
        System.out.println(result);
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int num = queue.size();
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < num; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(0, level);
        }
        return result;
    }
}
