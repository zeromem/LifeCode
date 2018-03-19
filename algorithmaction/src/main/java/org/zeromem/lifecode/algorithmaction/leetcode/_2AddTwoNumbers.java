package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * @author zeromem
 * @date 2018/3/19
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class _2AddTwoNumbers {
    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode index1 = l1.next, index2 = l2.next;
        ListNode cur = new ListNode((l1.val + l2.val) % 10);
        ListNode res = cur;
        int carry = (l1.val + l2.val) / 10;

        while (index1 != null && index2 != null) {
            cur.next = new ListNode((carry + index1.val + index2.val) % 10);
            carry = (carry + index1.val + index2.val) / 10;
            index1 = index1.next;
            index2 = index2.next;
            cur = cur.next;
        }

        // 如果l1 和 l2长度一样，最后只需检查进位
        if (index1 == null && index2 == null) {
            if (carry == 0) {
                return res;
            } else { // carry == 1
                cur.next = new ListNode(1);
                return res;
            }
        }

        // l1短，先遇到null
        if (index1 == null) {
            while (index2 != null) {
                cur.next = new ListNode((carry + index2.val) % 10);
                carry = (carry + index2.val) / 10;
                index2 = index2.next;
                cur = cur.next;
            }
            if (carry == 1) cur.next = new ListNode(1);
            return res;
        }

        // l2短，先遇到null
        if (index2 == null) {
            while (index1 != null) {
                cur.next = new ListNode((carry + index1.val) % 10);
                carry = (carry + index1.val) / 10;
                index1 = index1.next;
                cur = cur.next;
            }
            if (carry == 1) cur.next = new ListNode(1);
            return res;
        }
        return res;
    }
}
