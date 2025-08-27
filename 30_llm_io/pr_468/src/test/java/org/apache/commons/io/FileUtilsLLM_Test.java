package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class FileUtilsLLM_Test {

    @Test
    public void testCreateParentDirectories_NullFile() throws IOException {
        // Test creating parent directories for a null file
        File result = FileUtils.createParentDirectories(null);
        assertEquals(null, result, "Expected null result for null input");
    }

    @Test
    public void testCreateParentDirectories_ExistingParent() throws IOException {
        // Test creating parent directories for a file with an existing parent directory
        File parentDir = new File("existingParentDir");
        parentDir.mkdirs();
        File file = new File(parentDir, "testFile.txt");
        File result = FileUtils.createParentDirectories(file);
        assertEquals(parentDir, result, "Expected parent directory to be returned");
        parentDir.delete();
    }

    @Test
    public void testCreateParentDirectories_NonExistentParent() throws IOException {
        // Test creating parent directories for a file with a non-existent parent directory
        File parentDir = new File("nonExistentParentDir");
        File file = new File(parentDir, "testFile.txt");
        File result = FileUtils.createParentDirectories(file);
        assertEquals(parentDir, result, "Expected parent directory to be created and returned");
        parentDir.delete();
    }

    @Test
    public void testForceMkdir_NullDirectory() throws IOException {
        // Test forcing mkdir for a null directory
        assertThrows(NullPointerException.class, () -> FileUtils.forceMkdir(null), "Expected NullPointerException for null input");
    }

    @Test
    public void testForceMkdir_ExistingDirectory() throws IOException {
        // Test forcing mkdir for an existing directory
        File directory = new File("existingDir");
        directory.mkdirs();
        FileUtils.forceMkdir(directory);
        assertEquals(true, directory.exists(), "Expected directory to exist");
        directory.delete();
    }

    @Test
    public void testForceMkdir_NonExistentDirectory() throws IOException {
        // Test forcing mkdir for a non-existent directory
        File directory = new File("nonExistentDir");
        FileUtils.forceMkdir(directory);
        assertEquals(true, directory.exists(), "Expected directory to be created");
        directory.delete();
    }

    @Test
    public void testForceMkdirParent_NullFile() throws IOException {
        // Test forcing mkdir parent for a null file
        assertThrows(NullPointerException.class, () -> FileUtils.forceMkdirParent(null), "Expected NullPointerException for null input");
    }

    @Test
    public void testForceMkdirParent_ExistingParent() throws IOException {
        // Test forcing mkdir parent for a file with an existing parent directory
        File parentDir = new File("existingParentDir");
        parentDir.mkdirs();
        File file = new File(parentDir, "testFile.txt");
        FileUtils.forceMkdirParent(file);
        assertEquals(true, parentDir.exists(), "Expected parent directory to exist");
        parentDir.delete();
    }

    @Test
    public void testForceMkdirParent_NonExistentParent() throws IOException {
        // Test forcing mkdir parent for a file with a non-existent parent directory
        File parentDir = new File("nonExistentParentDir");
        File file = new File(parentDir, "testFile.txt");
        FileUtils.forceMkdirParent(file);
        assertEquals(true, parentDir.exists(), "Expected parent directory to be created");
        parentDir.delete();
    }
}