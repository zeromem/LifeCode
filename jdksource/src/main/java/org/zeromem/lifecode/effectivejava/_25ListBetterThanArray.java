package org.zeromem.lifecode.effectivejava;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2018/2/8
 * effective java #25
 * 列表优于数组，数组是协变的，列表是不变的。
 * 列表可在编译时检查类型错误，数组在运行时才能发现类型错误。
 *
 * 建议不要混合使用列表和数组，如List<String>[] la = new List<>[5];
 */
public class _25ListBetterThanArray {
    public static void main(String[] args) {
//        数组是协变的(covariant)。A是B的子类型，则A[]是B[]的子类型
        Object[] oa = new Long[3];
//        脏数据可以正常插入
        oa[0] = "hello world";
//        访问时异常
        System.out.println(Arrays.toString(oa));


//        列表是不变的(invariant)。任意的A和B，List<A>和List<B>没有任何关系
//        List<Object> ol = new ArrayList<Long>();
    }
}
