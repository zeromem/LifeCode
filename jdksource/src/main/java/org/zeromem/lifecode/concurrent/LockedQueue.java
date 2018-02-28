package org.zeromem.lifecode.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zeromem
 * @date 2018/2/24
 */
public class LockedQueue<T> {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final T[] queue;
    int head, tail, count;


    public LockedQueue(int capacity) {
        @SuppressWarnings("unchecked")
        T[] queue = (T[]) new Object[capacity];
        this.queue = queue;
    }

    public void enq(T t) {
        lock.lock();
        try {
            while (count == queue.length) {
                notFull.await();
            }
            queue[tail] = t;
            if (++tail == queue.length) {
                tail = 0;
            }
            count++;
//            if (count == 1) // Wrong! 不能只在队列由空变为非空时唤醒其他线程，会出现唤醒丢失。
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T deq() {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            T t = queue[head];
            if (++head == queue.length) {
                head = 0;
            }
            count--;
            notFull.signal();
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }
}
