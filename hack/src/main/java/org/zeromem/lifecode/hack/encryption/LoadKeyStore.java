package org.zeromem.lifecode.hack.encryption;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;

/**
 * @author zeromem
 * @date 2018/2/24
 * 使用java自带的keytool可以生成keystore文件
 * keytool -genkeypair -alias zeromem_first_test -storepass parallel -keypass parallel -keyalg RSA -keystore zerofirst.jks
 */
public class LoadKeyStore {
    public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, IOException, UnrecoverableEntryException, CertificateException {
        InputStream is = LoadKeyStore.class.getResourceAsStream("/zerofirst.jks");
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        // load keystore file using -storepass
        keyStore.load(is, "parallel".toCharArray());
        // load key using -keypass
        KeyStore.PasswordProtection keyPassword = new KeyStore.PasswordProtection("parallel".toCharArray());

        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("zeromem_first_test", keyPassword);
        System.out.println(privateKeyEntry);

        Certificate cert = keyStore.getCertificate("zeromem_first_test");
        PublicKey publicKey = cert.getPublicKey();
        PrivateKey privateKey = privateKeyEntry.getPrivateKey();

        System.out.println(publicKey);
        System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
    }
}
