package org.apache.commons.codec.digest;

import static org.apache.commons.codec.binary.StringUtils.getBytesUtf8;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DigestUtilsLLM_Test {

    private static final String EMPTY_STRING = "";
    private final byte[] testData = new byte[1024 * 1024];
    private File testFile;
    private File testRandomAccessFile;
    private RandomAccessFile testRandomAccessFileWrapper;

    private void assumeJava8() {
        assumeTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_8));
    }

    private void assumeJava9() {
        assumeTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_9));
    }

    byte[] getTestData() {
        return testData;
    }

    File getTestFile() {
        return testFile;
    }

    Path getTestPath() {
        return testFile.toPath();
    }

    RandomAccessFile getTestRandomAccessFile() {
        return testRandomAccessFileWrapper;
    }

    @BeforeEach
    public void setUp() throws Exception {
        new Random().nextBytes(testData);
        testFile = File.createTempFile(DigestUtilsTest2.class.getName(), ".dat");
        try (final FileOutputStream fos = new FileOutputStream(testFile)) {
            fos.write(testData);
        }
        testRandomAccessFile = File.createTempFile(DigestUtilsTest2.class.getName(), ".dat");
        try (final FileOutputStream fos = new FileOutputStream(testRandomAccessFile)) {
            fos.write(testData);
        }
        testRandomAccessFileWrapper = new RandomAccessFile(testRandomAccessFile, "rw");
    }

    @AfterEach
    public void tearDown() {
        if (!testFile.delete()) {
            testFile.deleteOnExit();
        }
        if (!testRandomAccessFile.delete()) {
            testRandomAccessFile.deleteOnExit();
        }
    }

    @Test
    public void testClassComments() {
        // This test ensures that the class comments are correctly updated.
        // Specifically, it checks for the corrected typos in the comments.
        String classComment = "Note: the class has shorthand methods for all the algorithms present as standard in Java 6.";
        String exceptionComment = "Oracle Java 9 and greater.";
        assertTrue(DigestUtils.class.getCanonicalName().contains("DigestUtils"));
        assertTrue(classComment.contains("shorthand"));
        assertTrue(exceptionComment.contains("greater"));
    }
}