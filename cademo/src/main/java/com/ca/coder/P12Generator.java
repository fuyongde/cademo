package com.ca.coder;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import javax.security.auth.x500.X500Principal;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * Created by fuyongde on 2017/7/2.
 */
public class P12Generator {

    static {
        // 系统添加BC加密算法 以后系统中调用的算法都是BC的算法
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 创建P12证书
     * @param CN
     * @param O
     * @param OU
     * @param L
     * @param ST
     * @param C
     * @param start
     * @param end
     * @param alias 别名
     * @param password 密码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws SignatureException
     * @throws InvalidKeyException
     * @throws KeyStoreException
     * @throws IOException
     * @throws CertificateException
     */
    public static byte[] generator(String CN, String O, String OU, String L, String ST, String C, Date start, Date end,
        String alias, String password
    ) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, KeyStoreException, IOException, CertificateException {
        StringBuilder DN = new StringBuilder();
        DN.append("CN").append("=").append(CN).append(",");
        DN.append("O").append("=").append(O).append(",");
        DN.append("OU").append("=").append(OU).append(",");
        DN.append("L").append("=").append(L).append(",");
        DN.append("ST").append("=").append(ST).append(",");
        DN.append("C").append("=").append(C);
        String issuer = DN.toString();
        String subject = DN.toString();

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair keyPair = kpg.generateKeyPair();

        X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
        certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
        certGen.setIssuerDN(new X500Principal(issuer));
        certGen.setNotBefore(start);
        certGen.setNotAfter(end);
        certGen.setSubjectDN(new X500Principal(subject));
        certGen.setPublicKey(keyPair.getPublic());
        certGen.setSignatureAlgorithm("SHA256WithRSAEncryption");

        X509Certificate cert = certGen.generateX509Certificate(keyPair.getPrivate());

        KeyStore store = KeyStore.getInstance("PKCS12");
        store.load(null, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        store.setKeyEntry(alias, keyPair.getPrivate(), password.toCharArray(), new Certificate[] { cert });
        store.store(outputStream, password.toCharArray());
        return outputStream.toByteArray();
    }

}
