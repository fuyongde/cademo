package com.ca.utils;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * HMACUtils Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>7 3, 2017</pre>
 */
public class HMACUtilsTest {

    private static Logger logger = LoggerFactory.getLogger(HMACUtils.class);

    /**
     * Method: encrypt2String(String source, String key)
     */
    @Test
    public void testEncrypt2String() throws Exception {
        String target = HMACUtils.encrypt2String("dafy", HMACUtils.DEFAULT_KEY);
        logger.info(target);
        assertEquals("09341D024E8004631C3B8BE11F545602", target);
    }

} 
