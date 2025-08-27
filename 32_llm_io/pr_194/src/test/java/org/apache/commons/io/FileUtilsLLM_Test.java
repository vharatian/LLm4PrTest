package org.apache.commons.io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.LinkOption;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    @Test
    public void testIsDirectory() {
        File existingDir = new File(System.getProperty("java.io.tmpdir"));
        File nonExistingDir = new File(System.getProperty("java.io.tmpdir") + "/nonExistingDir");
        File file = new File(System.getProperty("java.io.tmpdir") + "/testFile.txt");

        assertTrue(FileUtils.isDirectory(existingDir));
        assertFalse(FileUtils.isDirectory(nonExistingDir));
        assertFalse(FileUtils.isDirectory(file));
    }

    @Test
    public void testIsEmptyDirectory() throws IOException {
        File emptyDir = new File(System.getProperty("java.io.tmpdir") + "/emptyDir");
        File nonEmptyDir = new File(System.getProperty("java.io.tmpdir") + "/nonEmptyDir");
        File file = new File(System.getProperty("java.io.tmpdir") + "/testFile.txt");

        emptyDir.mkdir();
        nonEmptyDir.mkdir();
        new File(nonEmptyDir, "testFile.txt").createNewFile();

        assertTrue(FileUtils.isEmptyDirectory(emptyDir));
        assertFalse(FileUtils.isEmptyDirectory(nonEmptyDir));
        assertThrows(IOException.class, () -> FileUtils.isEmptyDirectory(file));
    }

    @Test
    public void testIsRegularFile() {
        File existingFile = new File(System.getProperty("java.io.tmpdir") + "/testFile.txt");
        File nonExistingFile = new File(System.getProperty("java.io.tmpdir") + "/nonExistingFile.txt");
        File dir = new File(System.getProperty("java.io.tmpdir"));

        assertTrue(FileUtils.isRegularFile(existingFile));
        assertFalse(FileUtils.isRegularFile(nonExistingFile));
        assertFalse(FileUtils.isRegularFile(dir));
    }
}