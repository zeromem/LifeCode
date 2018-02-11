package org.zeromem.lifecode.effectivejava;

/**
 * @author zeromem
 * @date 2018/2/11
 * 只在异常情况下使用try catch.
 * 不要利用ArrayIndexOutOfBoundsException来遍历数组
 * 标准遍历模式(foreach)会比使用异常的方式更快
 */
public class _57NotUseExceptionToTraverseArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        try {
            int i = 0;
            while (true) {
                System.out.println(arr[i++]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // 遇到该异常表示碰到数组末尾，结束循环
        }
    }
}
