package org.zeromem.lifecode.algorithmaction.leetcode;

import static org.zeromem.lifecode.algorithmaction.leetcode.ListNode.printListNode;

/**
 * @author zeromem
 * @date 2018/3/2
 */
public class _147InsertionSortList {
    public static void main(String[] args) {
        _147InsertionSortList test = new _147InsertionSortList();
        ListNode head = new ListNode(10);
        head.next = new ListNode(8);
        head.next.next = new ListNode(17);
        head.next.next.next = new ListNode(12);
        ListNode res = test.insertionSortList(head);
        printListNode(res);
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode res = head;
        ListNode subList = head.next;
        head.next = null;

        while (subList != null) {
            ListNode node = subList;
            subList = subList.next;
            node.next = null;
            res = insert(res, node);
        }
        return res;
    }

    public static ListNode insert(ListNode head, ListNode node) {
        if (node == null) {
            return head;
        }
        if (head == null) {
            head = node;
            return head;
        }
        if (node.val <= head.val) {
            node.next = head;
            head = node;
            return head;
        }

        ListNode cur = head.next;
        ListNode pre = head;
        while (cur != null) {
            if (node.val <= cur.val) {
                pre.next = node;
                node.next = cur;
                return head;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        pre.next = node;
        return head;
    }
}
