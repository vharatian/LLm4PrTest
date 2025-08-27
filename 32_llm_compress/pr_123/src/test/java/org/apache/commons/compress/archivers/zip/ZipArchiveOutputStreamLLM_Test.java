package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.*;

public class ZipArchiveOutputStreamLLM_Test {

    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = Files.createTempFile("test", ".zip");
    }

    @Test
    public void testCreateZipArchiveOutputStreamWithPath() throws IOException {
        try (ZipArchiveOutputStream zos = new ZipArchiveOutputStream(tempFile, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            assertNotNull(zos);
            assertTrue(zos.isSeekable());
        }
    }

    @Test
    public void testCreateArchiveEntryWithPath() throws IOException {
        try (ZipArchiveOutputStream zos = new ZipArchiveOutputStream(tempFile, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            Path inputPath = Files.createTempFile("input", ".txt");
            Files.write(inputPath, "test data".getBytes());

            ArchiveEntry entry = zos.createArchiveEntry(inputPath, "entryName.txt", LinkOption.NOFOLLOW_LINKS);
            assertNotNull(entry);
            assertEquals("entryName.txt", entry.getName());
        }
    }

    @Test
    public void testCreateArchiveEntryWithPathForDirectory() throws IOException {
        try (ZipArchiveOutputStream zos = new ZipArchiveOutputStream(tempFile, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            Path inputDir = Files.createTempDirectory("inputDir");

            ArchiveEntry entry = zos.createArchiveEntry(inputDir, "entryDir/", LinkOption.NOFOLLOW_LINKS);
            assertNotNull(entry);
            assertEquals("entryDir/", entry.getName());
        }
    }

    @Test
    public void testCreateArchiveEntryWithPathStreamFinished() throws IOException {
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(tempFile, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        zos.finish();

        Path inputPath = Files.createTempFile("input", ".txt");
        Files.write(inputPath, "test data".getBytes());

        IOException exception = assertThrows(IOException.class, () -> {
            zos.createArchiveEntry(inputPath, "entryName.txt", LinkOption.NOFOLLOW_LINKS);
        });

        assertEquals("Stream has already been finished", exception.getMessage());
    }
}