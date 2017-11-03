package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2017/11/1
 * Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:
Input:
  1
 / \
0   2

L = 1
R = 2

Output:
1
 \
  2

Example 2:
Input:
  3
 / \
0   4
 \
 2
/
1

L = 1
R = 3

Output:
  3
 /
 2
/
1
 */

public class _669TrimBST {
    public static void main(String[] args) {
        _669TrimBST test = new _669TrimBST();

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

        System.out.println(test.trimBST(root, 3, 7));
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        } else if (root.val > R) {
            return trimBST(root.left, L, R);
        } else if (root.val < L) {
            return trimBST(root.right, L, R);
        } else {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        }
    }
}

