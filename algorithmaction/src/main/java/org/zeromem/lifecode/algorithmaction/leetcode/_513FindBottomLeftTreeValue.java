package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zeromem
 * @date 2018/2/28
 */
public class _513FindBottomLeftTreeValue {
    public static void main(String[] args) {
        _513FindBottomLeftTreeValue test = new _513FindBottomLeftTreeValue();
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
//        root.right.left.right.left = new TreeNode(7);

        int bottomLeft = test.findBottomLeftValue(root);
        System.out.println(bottomLeft);
    }

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        int res = root.val;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.right != null) {
                queue.add(node.right);
                res = node.right.val;
            }
            if (node.left != null) {
                queue.add(node.left);
                res = node.left.val;
            }
        }
        return res;
    }
}
