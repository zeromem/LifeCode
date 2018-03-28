package org.zeromem.lifecode.hack;

/**
 * @author zeromem@qq.com
 * @date 2018/3/21
 */
public class JavaUnicodeChar {
    public static void main(String[] args) {
        // 一个char只能是16位的码点
        char a = '\u0000';
        System.out.println(a);

        StringBuilder builder = new StringBuilder();
        String unicode = "\\u4e2d";
        Integer codepoint = Integer.valueOf(unicode.substring(2), 16);
        builder.appendCodePoint(codepoint);
        builder.appendCodePoint(2291);
        System.out.println(builder.toString());
    }
}
