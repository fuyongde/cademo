package com.ca.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * SHAUtils Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>7 3, 2017</pre>
 */
public class SHAUtilsTest {
    private static Logger logger = LoggerFactory.getLogger(SHAUtilsTest.class);
    /**
     * Method: encrypt2String(String source)
     */
    @Test
    public void testEncrypt2String() throws Exception {
        String target = SHAUtils.encrypt2String("dafy");
        logger.info(target);
        assertEquals("7653F51C716F9C65EF4B2BC7969F284412388D3A", target);
    }

} 
