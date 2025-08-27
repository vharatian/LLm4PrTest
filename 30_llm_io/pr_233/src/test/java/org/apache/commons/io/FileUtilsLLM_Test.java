package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    @Test
    public void testIsDirectory() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        assertTrue(FileUtils.isDirectory(file));
        assertFalse(FileUtils.isDirectory(null));
    }

    @Test
    public void testIsFileNewer() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        Instant now = Instant.now();
        assertFalse(FileUtils.isFileNewer(file, now.toEpochMilli()));
        assertThrows(NullPointerException.class, () -> FileUtils.isFileNewer(null, now.toEpochMilli()));
    }

    @Test
    public void testIsFileOlder() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        Instant now = Instant.now();
        assertFalse(FileUtils.isFileOlder(file, now.toEpochMilli()));
        assertThrows(NullPointerException.class, () -> FileUtils.isFileOlder(null, now.toEpochMilli()));
    }

    @Test
    public void testIsRegularFile() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        assertFalse(FileUtils.isRegularFile(file));
        assertFalse(FileUtils.isRegularFile(null));
    }

    @Test
    public void testIsSymlink() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        assertFalse(FileUtils.isSymlink(file));
        assertFalse(FileUtils.isSymlink(null));
    }

    @Test
    public void testLineIterator() throws IOException {
        File file = new File(System.getProperty("java.io.tmpdir"), "testFile.txt");
        assertThrows(IOException.class, () -> FileUtils.lineIterator(file, "UTF-8"));
    }
}