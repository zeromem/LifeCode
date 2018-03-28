package org.zeromem.lifecode.hack.effectivejava;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zeromem
 * @date 2018/2/8
 */
public class _27GenericMethod {
    public static void main(String[] args) {
    }

    public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> s = new HashSet<>(s1);
        s.addAll(s2);
        return s;
    }
}
