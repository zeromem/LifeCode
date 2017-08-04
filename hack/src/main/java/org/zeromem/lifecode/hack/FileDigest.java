package org.zeromem.lifecode.hack;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by zeromem on 2017/8/2.
 */
public class FileDigest {
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		FileInputStream fis = new FileInputStream("D:\\server.log");
		String digest = DigestUtils.sha256Hex(fis);
		fis.close();
		System.out.println(digest);
		System.out.println(System.currentTimeMillis() - start);
	}
}
