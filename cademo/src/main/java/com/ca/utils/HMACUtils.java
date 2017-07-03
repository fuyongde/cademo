package com.ca.utils;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMACUtils {

    private final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * MAC算法可选以下多种算法
     *
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    public static final String HMAC = "HmacMD5";

    /**
     * 初始化HMAC密钥
     *
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(HMAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64Utils.encode(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] encrypt(byte[] data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey secretKey = new SecretKeySpec(Base64.decode(key), HMAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

    /**
     * HMAC加密
     * @param source
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] encrypt(String source, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        return encrypt(source.getBytes(DEFAULT_CHARSET), key);
    }

    /**
     * HMAC加密
     * @param source
     * @param key
     * @param toLowerCase
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String encrypt(String source, String key, boolean toLowerCase) throws NoSuchAlgorithmException, InvalidKeyException {
        return new String(Hex.encodeHex(encrypt(source.getBytes(DEFAULT_CHARSET), key), toLowerCase));
    }

    /**
     * HMAC加密
     * @param source
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String encrypt2String(String source, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        return new String(Hex.encodeHex(encrypt(source.getBytes(DEFAULT_CHARSET), key), false));
    }
}
