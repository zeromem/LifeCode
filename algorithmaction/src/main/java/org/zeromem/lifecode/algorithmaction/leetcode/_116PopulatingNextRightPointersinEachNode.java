package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zeromem
 * @date 2018/3/20
 */
public class _116PopulatingNextRightPointersinEachNode {
    public static void main(String[] args) {
        _116PopulatingNextRightPointersinEachNode test = new _116PopulatingNextRightPointersinEachNode();
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        test.connect(root);

        System.out.println(root.next);
        System.out.println(root.left.next);
    }

    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeLinkNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            TreeLinkNode cur = deque.removeFirst();
            int size = deque.size();
            if (cur.left != null) {
                deque.addLast(cur.left);
            }
            if (cur.right != null) {
                deque.addLast(cur.right);
            }

            for (int i = 0; i < size; i++) {
                TreeLinkNode next = deque.removeFirst();
                if (next.left != null) {
                    deque.addLast(next.left);
                }
                if (next.right != null) {
                    deque.addLast(next.right);
                }
                cur.next = next;
                cur = cur.next;
            }
        }
    }
}
