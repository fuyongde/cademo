package com.ca.utils;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static final String MD5 = "MD5";

    private final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] encrypt(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance(MD5);
        md5.update(data);
        return md5.digest();
    }

    /**
     * MD5 加密
     * @param source
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] encrypt(String source) throws NoSuchAlgorithmException {
        return encrypt(source.getBytes(DEFAULT_CHARSET));
    }

    /**
     * MD5 加密
     * @param source
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt(String source, boolean toLowerCase) throws NoSuchAlgorithmException {
        return new String(Hex.encodeHex(encrypt(source.getBytes(DEFAULT_CHARSET)), toLowerCase));
    }

    /**
     * MD5 加密
     * @param source
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt2String(String source) throws NoSuchAlgorithmException {
        return new String(Hex.encodeHex(encrypt(source.getBytes(DEFAULT_CHARSET)), false));
    }
}
