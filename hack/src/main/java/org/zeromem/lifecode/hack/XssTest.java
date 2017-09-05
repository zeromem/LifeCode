package org.zeromem.lifecode.hack;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zeromem on 2017/8/8.
 */
public class XssTest {
	public static AtomicLong a = new AtomicLong(0);
	public static int b = 0;

	public static final void go() {
		a.incrementAndGet();
		b++;
		try {
			go();
		} catch (Throwable throwable) {
			System.out.println(a);
			System.out.println(b);
			throwable.printStackTrace();
		}
	}

	public static void main(String[] args) {
		go();
	}

	public static void test() {

	}
}
