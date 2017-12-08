package org.zeromem.lifecode.hack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zeromem
 * @date 2017/11/20
 */
public class Java8Time {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH");
        String str = "20160901 12";
        LocalDateTime time = LocalDateTime.parse(str, formatter);
        System.out.println(time);
    }
}
