package org.zeromem.lifecode.hack;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

/**
 * Created by zeromem on 2017/8/2.
 */
public class FileDigest {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		testDigest();
	}

	public static void testApacheDigest() throws IOException {
		long start = System.currentTimeMillis();
		FileInputStream fis = new FileInputStream("D:\\server.log");
		String digest = DigestUtils.sha256Hex(fis);
		fis.close();
		System.out.println(digest);
		System.out.println(System.currentTimeMillis() - start);
	}

	public static void testSignature() throws NoSuchAlgorithmException {
		String input = "hello world";
		String privateKey = "sdhfahdglk";
		Signature rsa = Signature.getInstance("RSA");
	}

	public static void testDigest() throws NoSuchAlgorithmException, IOException {
		FileInputStream fis = new FileInputStream("D:\\server.log");
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		DigestInputStream dis = new DigestInputStream(fis, sha256);
		System.out.println(dis.available());
		final int BUFFER_LEN = 8192;
		byte[] buffer = new byte[BUFFER_LEN];
		int available = dis.read(buffer, 0, BUFFER_LEN);
		while (available > 0) {
//			for (int i = 0; i < available; i++) {
//				System.out.print((char) buffer[i]);
//			}
			available = dis.read(buffer, 0, BUFFER_LEN);
		}

		System.out.println();

		byte[] digest = sha256.digest();
		for (byte b : digest) {
			System.out.print(b);
		}
	}

	private static final char[] DIGITS_UPPER =
			{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

	private static String toHexString(byte[] bytes) {


		return null;
	}
}
