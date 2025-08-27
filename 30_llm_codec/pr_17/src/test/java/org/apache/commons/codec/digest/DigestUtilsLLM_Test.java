package org.apache.commons.codec.digest;

import static org.apache.commons.codec.binary.StringUtils.getBytesUtf8;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class DigestUtilsLLM_Test {

    private final byte[] testData = new byte[1024 * 1024];
    private File testFile;

    private void assumeJava8() {
        Assume.assumeTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_8));
    }

    private void assumeJava9() {
        Assume.assumeTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_9));
    }

    byte[] getTestData() {
        return testData;
    }

    File getTestFile() {
        return testFile;
    }

    @Before
    public void setUp() throws Exception {
        new Random().nextBytes(testData);
        testFile = File.createTempFile(DigestUtilsTest2.class.getName(), ".dat");
        try (final FileOutputStream fos = new FileOutputStream(testFile)) {
            fos.write(testData);
        }
    }

    @After
    public void tearDown() {
        if (!testFile.delete()) {
            testFile.deleteOnExit();
        }
    }

    @Test
    public void testGetSha3_224Digest() {
        assumeJava9();
        MessageDigest digest = DigestUtils.getSha3_224Digest();
        assertEquals("SHA3-224", digest.getAlgorithm());
    }

    @Test
    public void testGetSha3_256Digest() {
        assumeJava9();
        MessageDigest digest = DigestUtils.getSha3_256Digest();
        assertEquals("SHA3-256", digest.getAlgorithm());
    }

    @Test
    public void testGetSha3_384Digest() {
        assumeJava9();
        MessageDigest digest = DigestUtils.getSha3_384Digest();
        assertEquals("SHA3-384", digest.getAlgorithm());
    }

    @Test
    public void testGetSha3_512Digest() {
        assumeJava9();
        MessageDigest digest = DigestUtils.getSha3_512Digest();
        assertEquals("SHA3-512", digest.getAlgorithm());
    }

    @Test
    public void testGetSha384Digest() {
        MessageDigest digest = DigestUtils.getSha384Digest();
        assertEquals("SHA-384", digest.getAlgorithm());
    }

    @Test
    public void testGetSha512Digest() {
        MessageDigest digest = DigestUtils.getSha512Digest();
        assertEquals("SHA-512", digest.getAlgorithm());
    }
}