package com.ca.utils;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {

    public static final String RSA = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 初始化密钥对
     *
     * @return
     * @throws Exception
     */
    public static KeyPair initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        return keyPair;
    }

    /**
     * 取得私钥
     *
     * @param keyPair
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(KeyPair keyPair) throws Exception {
        return Base64Utils.encode(keyPair.getPrivate().getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyPair
     * @return
     * @throws Exception
     */
    public static String getPublicKey(KeyPair keyPair) throws Exception {
        return Base64Utils.encode(keyPair.getPublic().getEncoded());
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       加密数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = Base64Utils.decode(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return Base64Utils.encode(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = Base64Utils.decode(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // 验证签名是否正常
        return signature.verify(Base64Utils.decode(sign));
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param encrptData
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String encrptData, String key) throws Exception {
        byte[] data = Base64Utils.decode(encrptData);
        // 对密钥解密
        byte[] keyBytes = Base64Utils.decode(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param plainText
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String plainText, String key) throws Exception {
        byte[] data = plainText.getBytes(DEFAULT_CHARSET);
        // 对公钥解密
        byte[] keyBytes = Base64Utils.decode(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64Utils.encode(cipher.doFinal(data));
    }

}
