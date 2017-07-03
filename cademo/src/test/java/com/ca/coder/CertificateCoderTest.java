package com.ca.coder;

import com.ca.coder.CertificateCoder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * CertificateCoder Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 29, 2017</pre>
 */
public class CertificateCoderTest {

    private String password = "11111111";
    private String alias = "www.dafy.com";
    private String certificatePath = "/tmp/dafy.cer";
    private String keyStorePath = "/tmp/dafy.keystore";

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: encryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password)
     */
    @Test
    public void testEncryptByPrivateKey() throws Exception {
    }

    /**
     * Method: decryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password)
     */
    @Test
    public void testDecryptByPrivateKey() throws Exception {
    }

    /**
     * Method: encryptByPublicKey(byte[] data, String certificatePath)
     */
    @Test
    public void testEncryptByPublicKey() throws Exception {
    }

    /**
     * Method: decryptByPublicKey(byte[] data, String certificatePath)
     */
    @Test
    public void testDecryptByPublicKey() throws Exception {
    }

    /**
     * Method: verifyCertificate(String certificatePath)
     */
    @Test
    public void testVerifyCertificateCertificatePath() throws Exception {
    }

    /**
     * Method: verifyCertificate(Date date, String certificatePath)
     */
    @Test
    public void testVerifyCertificateForDateCertificatePath() throws Exception {
    }

    /**
     * Method: verify(byte[] data, String sign, String certificatePath)
     */
    @Test
    public void testVerify() throws Exception {
    }

    /**
     * Method: verifyCertificate(Date date, String keyStorePath, String alias, String password)
     */
    @Test
    public void testVerifyCertificateForDateKeyStorePathAliasPassword() throws Exception {
    }

    /**
     * Method: verifyCertificate(String keyStorePath, String alias, String password)
     */
    @Test
    public void testVerifyCertificateForKeyStorePathAliasPassword() throws Exception {
    }


    /**
     * Method: getPrivateKey(String keyStorePath, String alias, String password)
     */
    @Test
    public void testGetPrivateKey() throws Exception {

    }

    /**
     * Method: getPublicKey(String certificatePath)
     */
    @Test
    public void testGetPublicKey() throws Exception {

    }

    /**
     * Method: getCertificate(String certificatePath)
     */
    @Test
    public void testGetCertificateCertificatePath() throws Exception {
    }

    /**
     * Method: getCertificate(String keyStorePath, String alias, String password)
     */
    @Test
    public void testGetCertificateForKeyStorePathAliasPassword() throws Exception {
    }

    /**
     * Method: getKeyStore(String keyStorePath, String password)
     */
    @Test
    public void testGetKeyStore() throws Exception {
    }

    /**
     * Method: verifyCertificate(Date date, Certificate certificate)
     */
    @Test
    public void testVerifyCertificate() throws Exception {
    }

    @Test
    public void test() throws Exception {
        System.out.println("公钥加密——私钥解密");
        String inputStr = "Ceritifcate";
        byte[] data = inputStr.getBytes();

        byte[] encrypt = CertificateCoder.encryptByPublicKey(data, certificatePath);

        byte[] decrypt = CertificateCoder.decryptByPrivateKey(encrypt, keyStorePath, alias, password);
        String outputStr = new String(decrypt);

        System.out.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);

        // 验证数据一致
        assertArrayEquals(data, decrypt);

        // 验证证书有效
        assertTrue(CertificateCoder.verifyCertificate(certificatePath));

    }

    @Test
    public void testSign() throws Exception {
        System.out.println("私钥加密——公钥解密");

        String inputStr = "sign";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = CertificateCoder.encryptByPrivateKey(data,
                keyStorePath, alias, password);

        byte[] decodedData = CertificateCoder.decryptByPublicKey(encodedData,
                certificatePath);

        String outputStr = new String(decodedData);
        System.out.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);

        System.out.println("私钥签名——公钥验证签名");
        // 产生签名
        String sign = CertificateCoder.sign(encodedData, keyStorePath, alias, password);
        System.out.println("签名:" + sign);

        // 验证签名
        boolean status = CertificateCoder.verify(encodedData, sign, certificatePath);
        System.out.println("状态:" + status);
        assertTrue(status);

    }

} 
