package org.apache.commons.compress.archivers.tar;

import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Locale;

import static org.junit.Assert.*;

public class TarArchiveEntryLLM_Test {

    private static final String OS = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
    private static final String ROOT = OS.startsWith("windows") || OS.startsWith("netware") ? "C:\\" : "/";

    @Test
    public void testPathConstructor() throws IOException {
        Path tempFile = Files.createTempFile("taetest", ".txt");
        try {
            TarArchiveEntry entry = new TarArchiveEntry(tempFile);
            assertEquals(tempFile.getFileName().toString(), entry.getName());
            assertEquals(Files.size(tempFile), entry.getSize());
            assertTrue(entry.isFile());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testPathConstructorWithFileName() throws IOException {
        Path tempFile = Files.createTempFile("taetest", ".txt");
        try {
            TarArchiveEntry entry = new TarArchiveEntry(tempFile, "customName.txt");
            assertEquals("customName.txt", entry.getName());
            assertEquals(Files.size(tempFile), entry.getSize());
            assertTrue(entry.isFile());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testSetModTimeWithFileTime() throws IOException {
        Path tempFile = Files.createTempFile("taetest", ".txt");
        try {
            TarArchiveEntry entry = new TarArchiveEntry(tempFile);
            FileTime fileTime = Files.getLastModifiedTime(tempFile);
            entry.setModTime(fileTime);
            assertEquals(fileTime.toMillis() / 1000, entry.getModTime().getTime() / 1000);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testGetPath() throws IOException {
        Path tempFile = Files.createTempFile("taetest", ".txt");
        try {
            TarArchiveEntry entry = new TarArchiveEntry(tempFile);
            assertEquals(tempFile, entry.getPath());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testGetDirectoryEntriesWithPath() throws IOException {
        Path tempDir = Files.createTempDirectory("taetest");
        Path tempFile1 = Files.createFile(tempDir.resolve("file1.txt"));
        Path tempFile2 = Files.createFile(tempDir.resolve("file2.txt"));
        try {
            TarArchiveEntry entry = new TarArchiveEntry(tempDir);
            TarArchiveEntry[] entries = entry.getDirectoryEntries();
            assertEquals(2, entries.length);
            assertTrue(entries[0].getName().equals("file1.txt") || entries[0].getName().equals("file2.txt"));
            assertTrue(entries[1].getName().equals("file1.txt") || entries[1].getName().equals("file2.txt"));
        } finally {
            Files.deleteIfExists(tempFile1);
            Files.deleteIfExists(tempFile2);
            Files.deleteIfExists(tempDir);
        }
    }
}