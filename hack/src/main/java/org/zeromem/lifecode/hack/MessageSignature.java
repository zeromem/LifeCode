package org.zeromem.lifecode.hack;

import org.apache.commons.codec.binary.Hex;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zeromem on 2017/8/2.
 */
public class MessageSignature {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);
		KeyPair pair = generator.generateKeyPair();
		System.out.println(Hex.encodeHexString(pair.getPrivate().getEncoded()));
		System.out.println(pair.getPublic().toString());
	}
}
