package com.ca.utils;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AESUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>七月 3, 2017</pre>
 */
public class AESUtilsTest {

    private static Logger logger = LoggerFactory.getLogger(AESUtilsTest.class);

    /**
     * Method: encrypt(String original, String algorithm, SecretKey key)
     */
    @Test
    public void testEncrypt() throws Exception {
        String target = AESUtils.encrypt("dafy");
        logger.info(target);
        Assert.assertEquals("fn+yBqMyz4FS7k/7TZMvzQ==", target);
    }

    /**
     * Method: decrypt(byte[] cipherBytes, String algorithm, SecretKey key)
     */
    @Test
    public void testDecrypt() throws Exception {
        String source = AESUtils.decrypt("fn+yBqMyz4FS7k/7TZMvzQ==");
        logger.info(source);
        Assert.assertEquals("dafy", source);
    }

    @Test
    public void test1000() throws Exception {
        for (int i = 0; i < 1000; i++) {
            String target = AESUtils.encrypt("dafy");
            String source = AESUtils.decrypt(target);
        }
    }

} 
