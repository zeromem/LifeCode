package org.zeromem.lifecode.hack.effectivejava;

import java.util.Collection;

/**
 * @author zeromem
 * @date 2018/2/8
 * effective java #28 PECS (producer extends consumer super)
 * 用参数化类型表示一个T的生产者，则用<? extends T>
 * 用参数化类型表示T的消费者，则用<? super T>
 */
public class _28WildcardMethod {
}

class Stack<E> {
    public Stack(){}

    public void push(E e) {

    }

    public E pop() {
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

//  添加pushAll方法，应该用通配符使得方法更通用
//  pushAll时，参数的元素应该是? extends E
    public void pushAll(Iterable<? extends E> iter) {
        for (E e : iter) {
            push(e);
        }
    }


//  添加popAll方法，使用通配符
//  popAll时，参数元素应该是? super E
    public void popAll(Collection<? super E> col) {
        while (!isEmpty()) {
            col.add(pop());
        }
    }


}
