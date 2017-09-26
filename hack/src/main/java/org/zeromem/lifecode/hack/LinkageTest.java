package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/9/18.
 */
public class LinkageTest {
	public static void main(String[] args) {
		One one = null;
		Two two = new Two();
		System.out.println((Object) one == (Object) two);
	}

}

class Super {
	static {
		System.out.println("super");
	}
}

class One {
	static {
		System.out.println("One");
	}
}

class Two extends Super {
	static {
		System.out.println("two");
	}
}

