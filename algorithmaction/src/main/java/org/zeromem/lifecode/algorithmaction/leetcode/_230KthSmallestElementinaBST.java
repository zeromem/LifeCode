package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/20
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * <p>
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 * <p>
 * Credits:
 * Special thanks to @ts for adding this problem and creating all test cases.
 */
public class _230KthSmallestElementinaBST {
    private static int K;
    public static void main(String[] args) {
        _230KthSmallestElementinaBST test = new _230KthSmallestElementinaBST();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        System.out.println(test.kthSmallest(root, 2));
    }

    public int kthSmallest(TreeNode root, int k) {
        K = k;
        TreeNode target = kth(root);
        if (target == null) {
            return -1;
        }
        return target.val;
    }

    public static TreeNode kth(TreeNode node) {
        TreeNode target = null;
        if (node.left != null) {
            target = kth(node.left);
            if (target != null) {
                return target;
            }
        }
        if (K == 1) {
            return node;
        }
        K--;
        if (node.right != null) {
            target = kth(node.right);
        }
        return target;
    }
}
