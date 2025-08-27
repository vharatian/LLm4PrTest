package org.apache.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class PathUtilsLLM_Test {

    @TempDir
    public Path tempDir;

    @Test
    public void testIsDirectory() throws Exception {
        // Test with a directory
        assertTrue(PathUtils.isDirectory(tempDir, LinkOption.NOFOLLOW_LINKS));

        // Test with a file
        Path file = Files.createFile(tempDir.resolve("testFile.txt"));
        assertFalse(PathUtils.isDirectory(file, LinkOption.NOFOLLOW_LINKS));

        // Test with null
        assertFalse(PathUtils.isDirectory(null, LinkOption.NOFOLLOW_LINKS));
    }

    @Test
    public void testIsRegularFile() throws Exception {
        // Test with a regular file
        Path file = Files.createFile(tempDir.resolve("testFile.txt"));
        assertTrue(PathUtils.isRegularFile(file, LinkOption.NOFOLLOW_LINKS));

        // Test with a directory
        assertFalse(PathUtils.isRegularFile(tempDir, LinkOption.NOFOLLOW_LINKS));

        // Test with null
        assertFalse(PathUtils.isRegularFile(null, LinkOption.NOFOLLOW_LINKS));
    }
}