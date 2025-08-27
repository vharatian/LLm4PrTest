package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FileUtilsLLM_Test {

    @Test
    public void testReadFileToByteArray_FileNotFound() {
        File file = new File("non-existent-file.txt");
        assertThrows(IOException.class, () -> FileUtils.readFileToByteArray(file));
    }

    @Test
    public void testReadFileToString_FileNotFound() {
        File file = new File("non-existent-file.txt");
        assertThrows(IOException.class, () -> FileUtils.readFileToString(file, StandardCharsets.UTF_8));
    }

    @Test
    public void testReadFileToStringWithCharset_FileNotFound() {
        File file = new File("non-existent-file.txt");
        assertThrows(IOException.class, () -> FileUtils.readFileToString(file, "UTF-8"));
    }

    @Test
    public void testReadLines_FileNotFound() {
        File file = new File("non-existent-file.txt");
        assertThrows(IOException.class, () -> FileUtils.readLines(file, StandardCharsets.UTF_8));
    }

    @Test
    public void testReadLinesWithCharset_FileNotFound() {
        File file = new File("non-existent-file.txt");
        assertThrows(IOException.class, () -> FileUtils.readLines(file, "UTF-8"));
    }

    @Test
    public void testReadFileToByteArray_DirectoryInsteadOfFile() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        try {
            assertThrows(IOException.class, () -> FileUtils.readFileToByteArray(dir));
        } finally {
            FileUtils.deleteQuietly(dir);
        }
    }

    @Test
    public void testReadFileToString_DirectoryInsteadOfFile() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        try {
            assertThrows(IOException.class, () -> FileUtils.readFileToString(dir, StandardCharsets.UTF_8));
        } finally {
            FileUtils.deleteQuietly(dir);
        }
    }

    @Test
    public void testReadFileToStringWithCharset_DirectoryInsteadOfFile() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        try {
            assertThrows(IOException.class, () -> FileUtils.readFileToString(dir, "UTF-8"));
        } finally {
            FileUtils.deleteQuietly(dir);
        }
    }

    @Test
    public void testReadLines_DirectoryInsteadOfFile() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        try {
            assertThrows(IOException.class, () -> FileUtils.readLines(dir, StandardCharsets.UTF_8));
        } finally {
            FileUtils.deleteQuietly(dir);
        }
    }

    @Test
    public void testReadLinesWithCharset_DirectoryInsteadOfFile() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        try {
            assertThrows(IOException.class, () -> FileUtils.readLines(dir, "UTF-8"));
        } finally {
            FileUtils.deleteQuietly(dir);
        }
    }
}