package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2018/3/28
 * 合并含有K个元素的有序链表，并且作为一个有序链表的形式返回。分析并描述它的复杂度。
 */
public class _23MergekSortedLists {
    public static void main(String[] args) {

    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start + 1 == end) {
            return mergeTwoLists(lists[start], lists[end]);
        }
        int mid = start + ((end - start) >> 1);
        ListNode a = merge(lists, start, mid);
        ListNode b = merge(lists, mid + 1, end);
        return mergeTwoLists(a, b);
    }

    @SuppressWarnings("Duplicates")
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            ListNode l1Next = l1.next;
            l1.next = mergeTwoLists(l1Next, l2);
            return l1;
        }
        ListNode l2Next = l2.next;
        l2.next = mergeTwoLists(l1, l2Next);
        return l2;
    }
}
