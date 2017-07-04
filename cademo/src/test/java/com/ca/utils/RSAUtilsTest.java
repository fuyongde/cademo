package com.ca.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * RSAUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>7 4, 2017</pre>
 */
public class RSAUtilsTest {

    private static Logger logger = LoggerFactory.getLogger(RSAUtilsTest.class);

    private static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ1Kus1pwKa27YswQNXHcQ92f6M6ZGOWX56vhOqIhLnTjfCvIMA/YKHmoZ6uo3Jw4+fNOb5AjtfqlO21v7noGpJ7fSfN/5VfIYB7M3oW/slvXMrcbfd8Fn1RUZJtDs3fjRDqi/SMBppGZ2vDrn8L824wuvIOzMQurAzpuXRlXX5RAgMBAAECgYBSVC4onGTRHkiBpTUacHT2MgEm78Zh9fCAv8AjfmdyWJAf3ZqX5dRviacoxqIYoYw45UuHEIVz8H4ZWhtLdHiLRmuYi38UwQbY9ZY7i8KV+Wt9xK3UThjoXmyvYheHWrr1c9aAYq2lgsSI/3CEOu6oo/h66hbGMfC2oAAoSAblzQJBAN2c+SEExcXTK/R2WANHszPeunPDM+Ha2s+ln32LTGB9QXgdPNUpcZzxRfLfyCU7CF2SSM+rtlch9UlP17UxtlsCQQC1ssGB9XkcRoRBfTRzY31HtpRKwjwqjnRiICXXe9pgqbs7RF0uM/Vdx6T2Gm2W/Mp+peO21h/YOB98uBcA+HXDAkEAtbVH8HLhVttXZxLCB656mUU+zDx6Be5VC31Z0K6u6U7Kp5oRjxZ0OaL7H7GtghltOov/d4fr8nW9kWcOD1u7kQJAGu/paE7wKZT6vorFX+X8CMm0qnCpYtXDw1how82EzpfbPiogdl0Vn1Wdyy6X2K6ZhrUpUU3lYrTPVjokjuxG6QJBALkXHpqHX3QrnEVWGeL2BvDAGlLg5UdkzS9i+JPN5Im/8OZSom0d2+zYWnbqGVnxTgwhZVsacfjYIO5kbF2qADI=";
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdSrrNacCmtu2LMEDVx3EPdn+jOmRjll+er4TqiIS5043wryDAP2Ch5qGerqNycOPnzTm+QI7X6pTttb+56BqSe30nzf+VXyGAezN6Fv7Jb1zK3G33fBZ9UVGSbQ7N340Q6ov0jAaaRmdrw65/C/NuMLryDszELqwM6bl0ZV1+UQIDAQAB";

    /**
     * Method: sign(byte[] data, String privateKey)
     */
    @Test
    public void testSign() throws Exception {
        byte[] data = MD5Utils.encrypt("dafy");
        //私钥签名
        String sign = RSAUtils.sign(data, privateKey);
        logger.info(sign);
        //公钥验签
        boolean flag = RSAUtils.verify(data, publicKey, sign);
        assertEquals(true, flag);
    }

    /**
     * Method: encryptByPublicKey(String plainText, String key)
     */
    @Test
    public void testEncryptAndDecrypt() throws Exception {
        //公钥加密，私钥解密
        String target = RSAUtils.encryptByPublicKey("dafy", publicKey);
        logger.info(target);
        String source = RSAUtils.decryptByPrivateKey(target, privateKey);
        assertEquals("dafy", source);
    }

} 
