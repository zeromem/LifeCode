package org.zeromem.lifecode.effectivejava;

/**
 * @author zeromem
 * @date 2018/2/11
 */
public class _51StringBuilderBetterThanString {
    public static void main(String[] args) {
        String result1 = "";
        for (int i = 0; i < 10000; i++) {
            result1 += i;
        }
        System.out.println(result1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append(i);
        }
        System.out.println(sb);
    }
}
