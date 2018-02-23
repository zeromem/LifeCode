package org.zeromem.lifecode.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

/**
 * @author zeromem
 * @date 2018/2/23
 */
public class RSASign {
    private static final int KEY_SIZE = 4096;
    private static final KeyPair keyPair = createKeyPair(KEY_SIZE);
    public static final PublicKey publicKey = keyPair.getPublic();
    public static final PrivateKey privateKey = keyPair.getPrivate();
    private static final String ALGO_TYPE = "RSA";
    private static final String SIGN_TYPE = "SHA1WithRSA";

    private static KeyPair createKeyPair(int keySize) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGO_TYPE);
            generator.initialize(keySize);
            return generator.generateKeyPair();
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String sign(String content, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(SIGN_TYPE);
        signature.initSign(privateKey);
        signature.update(content.getBytes());
        byte[] signed = signature.sign();
        return Base64.getEncoder().encodeToString(signed);
    }

    public static boolean check(String content, PublicKey publicKey, String sign) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(SIGN_TYPE);
        signature.initVerify(publicKey);
        signature.update(content.getBytes());
        return signature.verify(Base64.getDecoder().decode(sign));
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, SignatureException {
        String content = "hello world, good bye world";
        String sign = sign(content, privateKey);
        System.out.println(sign);
        boolean check = check(content, publicKey, sign);
        System.out.println(check);

        // 修改签名
        String badSign = "1" + sign.substring(1);
        boolean badCheck1 = check(content, publicKey, badSign);
        System.out.println(badCheck1);

        // 修改内容
        String badContent = content + "bad";
        boolean badCheck2 = check(badContent, publicKey, sign);
        System.out.println(badCheck2);
    }
}
