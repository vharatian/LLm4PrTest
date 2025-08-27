package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileUtilsLLM_Test {

    private File tempDirFile;

    @BeforeEach
    public void setUp() throws Exception {
        tempDirFile = Files.createTempDirectory("tempDir").toFile();
    }

    @Test
    public void testCleanDirectory() throws IOException {
        File directory = new File(tempDirFile, "testCleanDirectory");
        directory.mkdirs();
        File file1 = new File(directory, "file1.txt");
        File file2 = new File(directory, "file2.txt");
        file1.createNewFile();
        file2.createNewFile();

        FileUtils.cleanDirectory(directory);

        assertTrue(directory.exists(), "Directory should exist");
        assertTrue(directory.isDirectory(), "Should be a directory");
        assertTrue(directory.list().length == 0, "Directory should be empty");
    }

    @Test
    public void testCleanDirectoryOnExit() throws IOException {
        File directory = new File(tempDirFile, "testCleanDirectoryOnExit");
        directory.mkdirs();
        File file1 = new File(directory, "file1.txt");
        File file2 = new File(directory, "file2.txt");
        file1.createNewFile();
        file2.createNewFile();

        FileUtils.cleanDirectoryOnExit(directory);

        assertTrue(directory.exists(), "Directory should exist");
        assertTrue(directory.isDirectory(), "Should be a directory");
        assertTrue(directory.list().length == 2, "Directory should not be empty immediately");

        // The files should be deleted on JVM exit, but we can't test that directly.
        // We can only ensure that the method runs without exceptions.
    }
}