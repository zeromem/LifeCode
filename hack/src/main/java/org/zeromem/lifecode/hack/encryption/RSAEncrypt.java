package org.zeromem.lifecode.hack.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

/**
 * @author zeromem
 * @date 2018/2/23
 */
public class RSAEncrypt {
    private static final int KEY_SIZE = 4096;
    private static final KeyPair keyPair = createKeyPair(KEY_SIZE);
    public static final PublicKey publicKey = keyPair.getPublic();
    public static final PrivateKey privateKey = keyPair.getPrivate();
    private static final String ALGO_TYPE = "RSA";

    private static KeyPair createKeyPair(int keySize) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGO_TYPE);
            generator.initialize(keySize);
            return generator.generateKeyPair();
        } catch (final NoSuchAlgorithmException e) {
            throw new Error(e);
        }
    }

    /**
     * 公钥加密，得到密文数据
     * @param publicKey
     * @param plainText
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encrypt(PublicKey publicKey, String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(ALGO_TYPE);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypt = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypt);
    }

    /**
     * 私钥解密，得到明文数据
     * @param privateKey
     * @param encryptData
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static String decrypt(PrivateKey privateKey, String encryptData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(ALGO_TYPE);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(encryptData));
        return new String(decrypt).trim();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        String s = "hello world, good bye world";
        String encode = encrypt(publicKey, s);

        String plain = decrypt(privateKey, encode);

        System.out.println(encode);
        System.out.println(plain);
    }

}
