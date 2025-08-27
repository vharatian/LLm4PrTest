package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    @Test
    public void testMkdirs() throws IOException {
        // Test case for mkdirs method
        File tempDir = new File(System.getProperty("java.io.tmpdir"), "testMkdirs");
        try {
            FileUtils.deleteQuietly(tempDir);
            File result = FileUtils.mkdirs(tempDir);
            assertTrue(result.exists() && result.isDirectory(), "Directory should be created");
        } finally {
            FileUtils.deleteQuietly(tempDir);
        }
    }

    @Test
    public void testRequireCanWrite() {
        // Test case for requireCanWrite method
        File tempFile = new File(System.getProperty("java.io.tmpdir"), "testRequireCanWrite.txt");
        try {
            FileUtils.touch(tempFile);
            assertDoesNotThrow(() -> FileUtils.requireCanWrite(tempFile, "tempFile"));
        } catch (IOException e) {
            fail("IOException should not be thrown");
        } finally {
            FileUtils.deleteQuietly(tempFile);
        }
    }

    @Test
    public void testRequireDirectory() {
        // Test case for requireDirectory method
        File tempDir = new File(System.getProperty("java.io.tmpdir"), "testRequireDirectory");
        try {
            FileUtils.deleteQuietly(tempDir);
            FileUtils.forceMkdir(tempDir);
            assertDoesNotThrow(() -> FileUtils.requireDirectory(tempDir, "tempDir"));
        } catch (IOException e) {
            fail("IOException should not be thrown");
        } finally {
            FileUtils.deleteQuietly(tempDir);
        }
    }

    @Test
    public void testToSuffixes() {
        // Test case for toSuffixes method
        String[] extensions = {"java", "xml"};
        String[] expectedSuffixes = {".java", ".xml"};
        assertArrayEquals(expectedSuffixes, FileUtils.toSuffixes(extensions));
    }
}