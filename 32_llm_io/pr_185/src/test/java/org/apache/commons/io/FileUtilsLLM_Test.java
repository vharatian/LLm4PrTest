package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    @TempDir
    File tempDir;

    @Test
    public void testMoveFilePreserveDate() throws IOException {
        File srcFile = new File(tempDir, "srcFile.txt");
        File destFile = new File(tempDir, "destFile.txt");

        // Create source file and write some content
        Files.write(srcFile.toPath(), "Test content".getBytes());

        // Set last modified date to a known value
        long lastModified = System.currentTimeMillis() - 10000;
        assertTrue(srcFile.setLastModified(lastModified));

        // Move file with preserveFileDate set to true
        FileUtils.moveFile(srcFile, destFile, true);

        // Verify the source file no longer exists
        assertFalse(srcFile.exists());

        // Verify the destination file exists and has the same content
        assertTrue(destFile.exists());
        assertEquals("Test content", new String(Files.readAllBytes(destFile.toPath())));

        // Verify the last modified date is preserved
        assertEquals(lastModified, destFile.lastModified());
    }

    @Test
    public void testMoveFileWithoutPreserveDate() throws IOException {
        File srcFile = new File(tempDir, "srcFile.txt");
        File destFile = new File(tempDir, "destFile.txt");

        // Create source file and write some content
        Files.write(srcFile.toPath(), "Test content".getBytes());

        // Set last modified date to a known value
        long lastModified = System.currentTimeMillis() - 10000;
        assertTrue(srcFile.setLastModified(lastModified));

        // Move file with preserveFileDate set to false
        FileUtils.moveFile(srcFile, destFile, false);

        // Verify the source file no longer exists
        assertFalse(srcFile.exists());

        // Verify the destination file exists and has the same content
        assertTrue(destFile.exists());
        assertEquals("Test content", new String(Files.readAllBytes(destFile.toPath())));

        // Verify the last modified date is not preserved
        assertNotEquals(lastModified, destFile.lastModified());
    }
}