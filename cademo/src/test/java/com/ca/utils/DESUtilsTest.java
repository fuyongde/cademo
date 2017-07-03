package com.ca.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DESUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>七月 3, 2017</pre>
 */
public class DESUtilsTest {

    private static Logger logger = LoggerFactory.getLogger(DESUtilsTest.class);

    /**
     * Method: encrypt(String source, String key)
     */
    @Test
    public void testEncrypt() throws Exception {
        String target = DESUtils.encrypt("dafy", DESUtils.DEFAULT_KEY);
        logger.info(target);
        Assert.assertEquals("G9CKQJZKZ6I=", target);
    }

    @Test
    public void testDecrypt() throws Exception {
        String source = DESUtils.decrypt2String("G9CKQJZKZ6I=", DESUtils.DEFAULT_KEY);
        logger.info(source);
        Assert.assertEquals("dafy", source);
    }

} 
