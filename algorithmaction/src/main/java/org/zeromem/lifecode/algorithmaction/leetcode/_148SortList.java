package org.zeromem.lifecode.algorithmaction.leetcode;

import static org.zeromem.lifecode.algorithmaction.leetcode.ListNode.printListNode;

/**
 * @author zeromem
 * @date 2018/3/2
 */
public class _148SortList {
    public static void main(String[] args) {
        _148SortList test = new _148SortList();

        ListNode head = new ListNode(9);
        head.next = new ListNode(10);
        head.next.next = new ListNode(7);

        ListNode res = test.sortList(head);
        printListNode(res);
    }

    /**
     * 先转换为数组，再用快排对数组排序，最后转换为链表
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = head;
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }

        int[] arr = new int[size];
        node = head;
        int pos = 0;
        while (node != null) {
            arr[pos++] = node.val;
            node = node.next;
        }

        // 对arr快排
        quickSort(arr, 0, arr.length - 1);

        node = head;
        pos = 0;
        while (node != null) {
            node.val = arr[pos++];
            node = node.next;
        }

        return head;
    }

    public static void quickSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int posPivot = partition(a, start, end);
        quickSort(a, start, posPivot - 1);
        quickSort(a, posPivot + 1, end);
    }

    public static int partition(int[] a, int start, int end) {
        int pivot = a[start];
        while (start < end) {
            while (a[end] >= pivot && start < end) {
                end--;
            }
            a[start] = a[end];
            while (a[start] <= pivot && start < end) {
                start++;
            }
            a[end] = a[start];;
        }
        a[start] = pivot;
        return start;
    }


}


