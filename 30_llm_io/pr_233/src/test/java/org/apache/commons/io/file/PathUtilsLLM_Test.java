package org.apache.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class PathUtilsLLM_Test {

    @TempDir
    public Path tempDir;

    @Test
    public void testIsDirectoryWithNullPath() {
        // Test the isDirectory method with a null path
        assertFalse(PathUtils.isDirectory(null));
    }

    @Test
    public void testIsDirectoryWithValidDirectory() throws IOException {
        // Test the isDirectory method with a valid directory
        assertTrue(PathUtils.isDirectory(tempDir));
    }

    @Test
    public void testIsDirectoryWithFile() throws IOException {
        // Test the isDirectory method with a file
        Path testFile = Files.createTempFile(tempDir, "prefix", null);
        assertFalse(PathUtils.isDirectory(testFile));
    }

    @Test
    public void testIsDirectoryWithNonExistentPath() throws IOException {
        // Test the isDirectory method with a non-existent path
        Path nonExistentDir = Files.createTempDirectory(getClass().getCanonicalName());
        Files.delete(nonExistentDir);
        assertFalse(PathUtils.isDirectory(nonExistentDir));
    }

    @Test
    public void testIsRegularFileWithNullPath() {
        // Test the isRegularFile method with a null path
        assertFalse(PathUtils.isRegularFile(null));
    }

    @Test
    public void testIsRegularFileWithValidFile() throws IOException {
        // Test the isRegularFile method with a valid file
        Path testFile = Files.createTempFile(tempDir, "prefix", null);
        assertTrue(PathUtils.isRegularFile(testFile));
    }

    @Test
    public void testIsRegularFileWithDirectory() throws IOException {
        // Test the isRegularFile method with a directory
        assertFalse(PathUtils.isRegularFile(tempDir));
    }

    @Test
    public void testIsRegularFileWithNonExistentFile() throws IOException {
        // Test the isRegularFile method with a non-existent file
        Path testFile = Files.createTempFile(tempDir, "prefix", null);
        Files.delete(testFile);
        assertFalse(PathUtils.isRegularFile(testFile));
    }
}