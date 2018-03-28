package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/28
 * Given a linked list, remove the nth node from the end of list and return its head.
 * <p>
 * For example,
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 */
public class _19RemoveNthNodeFromEndofList {
    public static void main(String[] args) {
        _19RemoveNthNodeFromEndofList test = new _19RemoveNthNodeFromEndofList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(test.removeNthFromEnd(head, 2));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode a = head;
        ListNode b = head;
        ListNode pre = null;
        int i = 0;
        while (i < n) {
            b = b.next;
            i++;
        }
        while (b != null) {
            pre = a;
            a = a.next;
            b = b.next;
        }
        if (pre != null) {
            pre.next = a.next;
            return head;
        }
        return a.next;
    }
}
