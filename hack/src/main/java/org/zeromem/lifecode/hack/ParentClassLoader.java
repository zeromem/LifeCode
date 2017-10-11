package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/10/9.
 */
public class ParentClassLoader {
	public static void main(String[] args) {
		ParentClassLoader test = new ParentClassLoader();
		ClassLoader cl1 = test.getClass().getClassLoader();
		System.out.println(cl1); // AppClassLoader

		ClassLoader cl2 = cl1.getParent();
		System.out.println(cl2); // ExtClassLoader

		ClassLoader cl3 = cl2.getParent();
		System.out.println(cl3); // cl3实际是一个BootstrapLoader，但在jvm中没有对应的实体，它是C++实现

		// cl3 is null.
//		ClassLoader cl4 = cl3.getParent();
//		System.out.println(cl4);
	}
}
