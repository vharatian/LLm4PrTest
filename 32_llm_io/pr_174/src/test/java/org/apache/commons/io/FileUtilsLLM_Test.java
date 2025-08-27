package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    @Test
    public void testMoveDirectoryToDirectory_CreateDestDir() throws IOException {
        File srcDir = new File("srcDir");
        File destDir = new File("destDir");
        File newDestDir = new File(destDir, srcDir.getName());

        // Setup: Create source directory
        FileUtils.forceMkdir(srcDir);

        // Test: Destination directory does not exist, createDestDir is true
        FileUtils.moveDirectoryToDirectory(srcDir, destDir, true);

        // Verify: Destination directory should be created and source directory moved
        assertTrue(newDestDir.exists());
        assertTrue(newDestDir.isDirectory());
        assertFalse(srcDir.exists());

        // Cleanup
        FileUtils.deleteDirectory(destDir);
    }

    @Test
    public void testMoveDirectoryToDirectory_DestDirExistsNotDirectory() {
        File srcDir = new File("srcDir");
        File destDir = new File("destDir");

        // Setup: Create source directory and destination file
        try {
            FileUtils.forceMkdir(srcDir);
            FileUtils.touch(destDir);
        } catch (IOException e) {
            fail("Setup failed: " + e.getMessage());
        }

        // Test: Destination exists but is not a directory
        IOException exception = assertThrows(IOException.class, () -> {
            FileUtils.moveDirectoryToDirectory(srcDir, destDir, true);
        });

        // Verify: Exception message
        assertEquals("Destination '" + destDir + "' is not a directory", exception.getMessage());

        // Cleanup
        FileUtils.deleteQuietly(srcDir);
        FileUtils.deleteQuietly(destDir);
    }

    @Test
    public void testMoveDirectoryToDirectory_DestDirDoesNotExistCreateDestDirFalse() {
        File srcDir = new File("srcDir");
        File destDir = new File("destDir");

        // Setup: Create source directory
        try {
            FileUtils.forceMkdir(srcDir);
        } catch (IOException e) {
            fail("Setup failed: " + e.getMessage());
        }

        // Test: Destination directory does not exist, createDestDir is false
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> {
            FileUtils.moveDirectoryToDirectory(srcDir, destDir, false);
        });

        // Verify: Exception message
        assertEquals("Destination directory '" + destDir + "' does not exist [createDestDir=false]", exception.getMessage());

        // Cleanup
        FileUtils.deleteQuietly(srcDir);
    }
}