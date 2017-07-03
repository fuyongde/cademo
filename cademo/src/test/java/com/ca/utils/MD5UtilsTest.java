package com.ca.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * MD5Utils Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>7 3, 2017</pre>
 */
public class MD5UtilsTest {

    private static Logger logger = LoggerFactory.getLogger(MD5UtilsTest.class);

    /**
     * Method: encrypt2String(String source)
     */
    @Test
    public void testEncrypt2String() throws Exception {
        String target = MD5Utils.encrypt2String("dafy");
        logger.info(target);
        assertEquals("2A2CDDDAF5B8A080AF2C3CF718832677", target);
    }


} 
