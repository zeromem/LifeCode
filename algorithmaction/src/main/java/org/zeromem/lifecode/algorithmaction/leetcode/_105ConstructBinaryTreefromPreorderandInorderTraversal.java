package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem@qq.com
 * @date 2018/3/19
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * <p>
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class _105ConstructBinaryTreefromPreorderandInorderTraversal {
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, inorder, 0, inorder.length - 1);
    }

    public static TreeNode build(int[] preorder, int preStart, int[] inorder, int inStart, int inEnd) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        int inRootPos = inStart;
        for (; inRootPos < inEnd; inRootPos++) {
            if (inorder[inRootPos] == rootVal) {
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preStart + 1, inorder, inStart, inRootPos - 1);
        root.right = build(preorder, preStart + inRootPos - inStart + 1, inorder, inRootPos + 1, inEnd);
        return root;
    }
}
