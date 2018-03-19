package org.zeromem.lifecode.algorithmaction.leetcode;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zeromem@qq.com
 * @date 2018/3/19
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * <p>
 * <p>
 * For example, the following two linked lists:
 * <p>
 * A:          a1 → a2
 *                    ↘
 *                      c1 → c2 → c3
 *                    ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 */
public class _160IntersectionofTwoLinkedLists {
    public static void main(String[] args) {

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        ListNode node = headA;
        while (node != null) {
            set.add(node.hashCode());
            node = node.next;
        }

        node = headB;
        while (node != null) {
            if (set.contains(node.hashCode())) {
                return node;
            }
            node = node.next;
        }
        return null;
    }
}
