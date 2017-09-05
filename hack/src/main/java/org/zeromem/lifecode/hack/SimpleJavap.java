package org.zeromem.lifecode.hack;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by zeromem on 2017/8/8.
 * 简单的反编译指令功能javap -cv
 */
public class SimpleJavap {
	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\zeromem\\Desktop\\QuickTest.class";
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), Charset.defaultCharset()));
		FileInputStream in = new FileInputStream(path);
		byte[] all = new byte[in.available()];
		in.read(all, 0, in.available());


		byte[] magicBytes = new byte[4];
		in.read(magicBytes, 0, 4);
		StringBuilder magicSB = new StringBuilder();
		for (byte b : magicBytes) {
			magicSB.append(String.format("%02x", b));
		}
		String magic = magicSB.toString();
		System.out.println("magic: " + magic);

		System.out.println(in.available());
		byte[] minorBytes = new byte[20];
		in.read(minorBytes, 4, 2);
		for (byte minorByte : minorBytes) {
			System.out.println(minorByte);
		}
	}
}
