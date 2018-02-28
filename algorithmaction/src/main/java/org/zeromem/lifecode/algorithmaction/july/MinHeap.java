package org.zeromem.lifecode.algorithmaction.july;

/**
 * @author zeromem
 * @date 2018/1/17
 * 最小堆，可用于选择N个数中最大的K个数
 */
public class MinHeap {
    private int[] heap;

    public MinHeap(int[] a) {
        heap = a;
        build();
    }

    private void build() {
        for (int i = (heap.length) / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int i) {
        int l = left(i);
        int r = right(i);

        int smallest = i;
        if (l < heap.length && heap[l] < heap[i]) {
            smallest = l;
        }
        if (r < heap.length && heap[r] < heap[smallest]) {
            smallest = r;
        }
        if (i == smallest) {
            return;
        }
        swap(i, smallest);
        heapify(smallest);
    }

    // 获取右结点的数组下标
    private int right(int i) {
        return (i + 1) << 1;
    }

    // 获取左结点的数组下标
    private int left(int i) {
        return ((i + 1) << 1) - 1;
    }

    // 交换元素位置
    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    // 获取对中的最小的元素，根元素
    public int getRoot() {
        return heap[0];
    }

    // 替换根元素，并重新heapify
    public void setRoot(int root) {
        heap[0] = root;
        heapify(0);
    }
}
