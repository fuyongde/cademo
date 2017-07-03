package com.ca.utils;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtils {

    public static final String SHA = "SHA";
    private final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance(SHA);
        sha.update(data);
        return sha.digest();
    }

    /**
     * SHA加密
     * @param source
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] encrypt(String source) throws NoSuchAlgorithmException {
        return encrypt(source.getBytes(DEFAULT_CHARSET));
    }

    /**
     * SHA加密
     * @param source
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt(String source, boolean toLowerCase) throws NoSuchAlgorithmException {
        return new String(Hex.encodeHex(encrypt(source.getBytes(DEFAULT_CHARSET)), toLowerCase));
    }

    /**
     * SHA加密
     * @param source
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt2String(String source) throws NoSuchAlgorithmException {
        return new String(Hex.encodeHex(encrypt(source.getBytes(DEFAULT_CHARSET)), false));
    }
}
