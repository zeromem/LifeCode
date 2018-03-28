package org.zeromem.lifecode.hack;

import java.math.BigDecimal;

/**
 * @author zeromem
 * @date 2018/3/1
 */
public class MathCeil {
    public static void main(String[] args) {
        System.out.println(new BigDecimal(0.4));
        long max = Long.MAX_VALUE;
        double bigger = max * 200.0;
        bigger += 0.5;
        System.out.println(new BigDecimal(bigger));
        System.out.println(new BigDecimal(Math.ceil(bigger)));
    }
}
