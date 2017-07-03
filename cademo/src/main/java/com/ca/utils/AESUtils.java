package com.ca.utils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by fuyongde on 2017/7/3.
 */
public class AESUtils {

    private static final String AES = "AES";

    private static final Charset UTF_8 = Charset.forName("UTF8");

    //默认的key
    private static final SecretKey DEFAULT_KEY = new SecretKeySpec("1234567890123456".getBytes(UTF_8), AES);

    //默认的偏移向量
    private static IvParameterSpec DEFAULT_IV = new IvParameterSpec("1234567890123456".getBytes(UTF_8));

    public static SecretKey getKey() throws NoSuchAlgorithmException {
        //获取密钥生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        //初始化，DES算法必须是56位，DESede算法可以是112位或168位，AES算法可以是128、192、256位
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    /**
     * 加密
     *
     * @param original  原文
     * @param algorithm 算法
     * @param key       密钥
     * @return result
     * @throws NoSuchPaddingException NoSuchPaddingException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeyException InvalidKeyException
     * @throws BadPaddingException BadPaddingException
     * @throws IllegalBlockSizeException IllegalBlockSizeException
     */
    private static byte[] encrypt(String original, String algorithm, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] sourceBytes = original.getBytes(UTF_8);
        return cipher.doFinal(sourceBytes);
    }

    /**
     * 解密
     *
     * @param cipherBytes 密文的byte数组
     * @param algorithm   算法
     * @param key         密钥
     * @return result
     * @throws NoSuchPaddingException NoSuchPaddingException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeyException InvalidKeyException
     * @throws BadPaddingException BadPaddingException
     * @throws IllegalBlockSizeException IllegalBlockSizeException
     */
    private static String decrypt(byte[] cipherBytes, String algorithm, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //创建密码器
        Cipher cipher = Cipher.getInstance(algorithm);
        //初始化
        cipher.init(Cipher.DECRYPT_MODE, key);
        //解密
        byte[] originalBytes = cipher.doFinal(cipherBytes);
        //重新显示原文
        return new String(originalBytes, UTF_8);
    }

    /**
     * 加密
     *
     * @param original 原文
     * @return result
     * @throws IllegalBlockSizeException IllegalBlockSizeException
     * @throws InvalidKeyException InvalidKeyException
     * @throws BadPaddingException BadPaddingException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws NoSuchPaddingException NoSuchPaddingException
     */
    public static String encrypt(String original) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        byte[] cipherBytes = encrypt(original, AES, DEFAULT_KEY);
        return Base64Utils.encode(cipherBytes);
    }

    /**
     * 解密
     *
     * @param cipher 密文
     * @return result
     * @throws IllegalBlockSizeException IllegalBlockSizeException
     * @throws InvalidKeyException InvalidKeyException
     * @throws BadPaddingException BadPaddingException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws NoSuchPaddingException NoSuchPaddingException
     */
    public static String decrypt(String cipher) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        return decrypt(Base64Utils.decode(cipher), AES, DEFAULT_KEY);
    }
}
