package com.ca.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.SecureRandom;

/**
 * DES加密工具类
 * DES加密key的长度必须为56
 * Created by fuyongde on 2017/7/3.
 */
public class DESUtils {

    public static final String DES = "DES";

    private final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public final static String DEFAULT_KEY = "1i/QQ51PMg4=";

    /**
     * 生成密钥
     *
     * @param seed
     * @return
     * @throws Exception
     */
    public static String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;
        if (seed != null) {
            secureRandom = new SecureRandom(Base64Utils.decode(seed));
        } else {
            secureRandom = new SecureRandom();
        }
        KeyGenerator kg = KeyGenerator.getInstance(DES);
        kg.init(secureRandom);
        SecretKey secretKey = kg.generateKey();
        return Base64Utils.encode(secretKey.getEncoded());
    }

    /**
     * 生成密钥
     *
     * @return
     * @throws Exception
     */
    public static String initKey() throws Exception {
        return initKey(null);
    }

    /**
     * DES加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String key) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(Base64Utils.decode(key));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /**
     * DES解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(byte[] data, String key) throws Exception {
        //创建密码器
        DESKeySpec desKeySpec = new DESKeySpec(Base64Utils.decode(key));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance(DES);
        //初始化
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        //解密
        byte[] originalBytes = cipher.doFinal(data);
        //重新显示原文
        return new String(originalBytes, DEFAULT_CHARSET);
    }

    /**
     * DES加密
     * @param source
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String source, String key) throws Exception {
        byte[] target = encrypt(source.getBytes(DEFAULT_CHARSET), key);
        return Base64Utils.encode(target);
    }

    /**
     * DES解密
     * @param target
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt2String(String target, String key) throws Exception {
        return decrypt(Base64Utils.decode(target), key);
    }
}
