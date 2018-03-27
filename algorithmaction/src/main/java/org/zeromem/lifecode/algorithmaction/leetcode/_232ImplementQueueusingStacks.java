package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Stack;

/**
 * @author zeromem
 * @date 2018/3/21
 */
public class _232ImplementQueueusingStacks {
    public static void main(String[] args) {

    }

}

class MyQueue {
    Stack<Integer> bottom = new Stack<>();
    Stack<Integer> top = new Stack<>();
    /** Initialize your data structure here. */
    public MyQueue() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        bottom.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (top.empty()) {
            while (!bottom.empty()) {
                top.push(bottom.pop());
            }
        }
        return top.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (top.empty()) {
            while (!bottom.empty()) {
                top.push(bottom.pop());
            }
        }
        return top.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return bottom.empty() && top.empty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
