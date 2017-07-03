package com.ca.coder;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Date;

/**
 * P12Generator Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>七月 2, 2017</pre>
 */
public class P12GeneratorTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: generator(String CN, String O, String OU, String L, String ST, String C, Date start, Date end, String alias, String password)
     */
    @Test
    public void testGenerator() throws Exception {
        String CN = "www.dafy.com";
        String O = "IPO";
        String OU = "dafy";
        String L = "shenzhen";
        String ST = "guangdong";
        String C = "CN";
        Date start = new Date();
        long year = 365 * 24 * 60 * 60 * 1000L;
        Date end = new Date(System.currentTimeMillis() + year);
        byte[] bytes = P12Generator.generator(CN, O, OU, L, ST, C, start, end, "dafy", "111111");
        FileUtils.writeByteArrayToFile(new File("/tmp/dafy.p12"), bytes);
    }


} 
