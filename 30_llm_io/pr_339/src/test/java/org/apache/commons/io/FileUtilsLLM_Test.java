package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

public class FileUtilsLLM_Test {

    @Test
    public void testDelete_NullFile() {
        assertThrows(NullPointerException.class, () -> FileUtils.delete(null));
    }

    @Test
    public void testDeleteDirectory_NullDirectory() {
        assertThrows(NullPointerException.class, () -> FileUtils.deleteDirectory(null));
    }

    @Test
    public void testTouch_NullFile() {
        assertThrows(NullPointerException.class, () -> FileUtils.touch(null));
    }

    @Test
    public void testDelete_NonExistentFile() {
        File nonExistentFile = new File("nonExistentFile.txt");
        assertThrows(IOException.class, () -> FileUtils.delete(nonExistentFile));
    }

    @Test
    public void testDeleteDirectory_NonExistentDirectory() {
        File nonExistentDirectory = new File("nonExistentDirectory");
        assertThrows(IOException.class, () -> FileUtils.deleteDirectory(nonExistentDirectory));
    }

    @Test
    public void testTouch_CreateNewFile() throws IOException {
        File newFile = new File("newFile.txt");
        try {
            FileUtils.touch(newFile);
            assertNotNull(newFile);
            assertEquals(0, Files.size(newFile.toPath()));
        } finally {
            Files.deleteIfExists(newFile.toPath());
        }
    }
}