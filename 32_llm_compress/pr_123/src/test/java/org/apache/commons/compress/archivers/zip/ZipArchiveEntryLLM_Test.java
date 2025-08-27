package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.zip.ZipException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ZipArchiveEntryLLM_Test {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testZipArchiveEntryPathConstructor() throws IOException {
        Path tempFile = Files.createTempFile("testfile", ".txt");
        Files.write(tempFile, "test content".getBytes());
        ZipArchiveEntry entry = new ZipArchiveEntry(tempFile, "testfile.txt");

        assertEquals("testfile.txt", entry.getName());
        assertEquals(Files.size(tempFile), entry.getSize());
        assertEquals(Files.getLastModifiedTime(tempFile).toMillis(), entry.getTime());

        Files.delete(tempFile);
    }

    @Test
    public void testZipArchiveEntryPathConstructorDirectory() throws IOException {
        Path tempDir = Files.createTempDirectory("testdir");
        ZipArchiveEntry entry = new ZipArchiveEntry(tempDir, "testdir");

        assertEquals("testdir/", entry.getName());
        assertTrue(entry.isDirectory());

        Files.delete(tempDir);
    }

    @Test
    public void testSetTimeFileTime() {
        ZipArchiveEntry entry = new ZipArchiveEntry("testfile.txt");
        FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
        entry.setTime(fileTime);

        assertEquals(fileTime.toMillis(), entry.getTime());
    }

    @Test
    public void testZipArchiveEntryPathConstructorWithLinkOption() throws IOException {
        Path tempFile = Files.createTempFile("testfile", ".txt");
        Files.write(tempFile, "test content".getBytes());
        LinkOption[] options = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
        ZipArchiveEntry entry = new ZipArchiveEntry(tempFile, "testfile.txt", options);

        assertEquals("testfile.txt", entry.getName());
        assertEquals(Files.size(tempFile), entry.getSize());
        assertEquals(Files.getLastModifiedTime(tempFile, options).toMillis(), entry.getTime());

        Files.delete(tempFile);
    }
}