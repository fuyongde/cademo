package com.ca.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * Base64Utils Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>7 3, 2017</pre>
 */
public class Base64UtilsTest {

    private static Logger logger = LoggerFactory.getLogger(Base64UtilsTest.class);

    /**
     * Method: encode(String source)
     */
    @Test
    public void testEncodeSource() throws Exception {
        String target = Base64Utils.encode("dafy");
        logger.info(target);
        assertEquals("ZGFmeQ==", target);
    }

    /**
     * Method: decode2String(String target)
     */
    @Test
    public void testDecode2String() throws Exception {
        String source = Base64Utils.decode2String("ZGFmeQ==");
        logger.info(source);
        assertEquals("dafy", source);
    }

} 
