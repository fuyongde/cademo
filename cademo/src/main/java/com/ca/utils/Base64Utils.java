package com.ca.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.Charset;

public class Base64Utils {

    private final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 对byte数组进行Base64编码
     * @param data
     * @return
     */
    public static String encode(byte[] data) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(data);
    }

    /**
     * 对Base64编码进行解码
     * @param target
     * @return
     * @throws IOException
     */
    public static byte[] decode(String target) throws IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        return base64Decoder.decodeBuffer(target);
    }

    /**
     * 对byte数组进行Base64编码
     * @param source
     * @return
     */
    public static String encode(String source) {
        return encode(source.getBytes(DEFAULT_CHARSET));
    }

    /**
     * 对Base64编码进行解码
     * @param target
     * @return
     * @throws IOException
     */
    public static String decode2String(String target) throws IOException {
        return new String(decode(target));
    }
}
