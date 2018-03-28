package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/28
 */
public class _21MergeTwoSortedLists {
    public static void main(String[] args) {

    }

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
