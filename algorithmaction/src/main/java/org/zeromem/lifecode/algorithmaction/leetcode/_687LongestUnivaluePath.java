package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/12/5
 *
 * <p>
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
 * <p>
 * Note: The length of path between two nodes is represented by the number of edges between them.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * <p>
 * 5
 * / \
 * 4   5
 * / \   \
 * 1   1   5
 * Output:
 * <p>
 * 2
 * Example 2:
 * <p>
 * Input:
 * <p>
 * 1
 * / \
 * 4   5
 * / \   \
 * 4   4   5
 * Output:
 * <p>
 * 2
 * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
public class _687LongestUnivaluePath {
    private int res;

    public static void main(String[] args) {

    }

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sub(root);
        return res;
    }

    private int sub(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = sub(node.left);
        int right = sub(node.right);
        if (node.left != null && node.left.val == node.val) {
            left++;
        } else {
            left = 0;
        }
        if (node.right != null && node.right.val == node.val) {
            right++;
        } else {
            right = 0;
        }
        res = Math.max(res, left + right);
        return Math.max(left, right);
    }
}
