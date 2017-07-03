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

    private String key = null;

    @Before
    public void before() throws Exception {
        key = "7k+PiAUf5RKzuvz19IANz4bm98BzcbN383HymhtiUPItr5QcDSGYulNOZFRYJWkovAGDo+B5qBfd3oR8gPTiww==";
        logger.info(key);
    }

    /**
     * Method: encrypt2String(String source, String key)
     */
    @Test
    public void testEncrypt2String() throws Exception {
        String target = HMACUtils.encrypt2String("dafy", key);
        logger.info(target);
        assertEquals("09341D024E8004631C3B8BE11F545602", target);
    }

} 
