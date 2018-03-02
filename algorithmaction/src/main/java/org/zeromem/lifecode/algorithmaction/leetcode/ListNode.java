package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/2
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static void printListNode(ListNode node) {
        ListNode n = node;
        while (n != null) {
            System.out.print(n.val + "->");
            n = n.next;
        }
        System.out.println("null");
    }
}
