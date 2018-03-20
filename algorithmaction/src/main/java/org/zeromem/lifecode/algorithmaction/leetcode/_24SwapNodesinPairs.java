package org.zeromem.lifecode.algorithmaction.leetcode;

import static org.zeromem.lifecode.algorithmaction.leetcode.ListNode.printListNode;

/**
 * @author zeromem
 * @date 2018/3/2
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * <p>
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 */
public class _24SwapNodesinPairs {
    public static void main(String[] args) {
        _24SwapNodesinPairs test = new _24SwapNodesinPairs();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode res = test.swapPairs(head);
        printListNode(res);
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode a = head;
        ListNode b = head.next;
        a.next = b.next;
        b.next = a;
        a.next = swapPairs(a.next);
        return b;
    }

}
