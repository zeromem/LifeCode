package org.zeromem.lifecode.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author zeromem
 * @date 2018/2/23
 * 对称加密的代表性算法
 * 加密解密使用相同的密钥，密钥分发成为关键。
 *
 */
public class AESEncrypt {
    public static final String KEY = "_zeromem@qq.com_";
    private static final String AES_TYPE = "AES/CBC/PKCS5Padding";
    private static byte[] IV = {0x30, 0x31, 0x30, 0x32, 0x30, 0x33, 0x30, 0x34, 0x30, 0x35, 0x30, 0x36, 0x30, 0x37, 0x30, 0x38};

    public static String encrypt(String keyStr, String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        SecretKeySpec key = generateKey(keyStr);
        Cipher cipher = Cipher.getInstance(AES_TYPE);
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV));
        byte[] encrypt = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypt);
    }

    public static String decrypt(String keyStr, String encryptData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        SecretKeySpec key = generateKey(keyStr);
        Cipher cipher = Cipher.getInstance(AES_TYPE);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV));
        byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(encryptData));
        return new String(decrypt).trim();
    }

    private static SecretKeySpec generateKey(String key) {
        return new SecretKeySpec(key.getBytes(), "AES");
    }


    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        String line = "hello world";
        String encode = encrypt(KEY, line);
        System.out.println(encode);
        String plain = decrypt(KEY, encode);
        System.out.println(plain);
    }
}
