package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.junit.jupiter.api.Test;

public class SevenZOutputFileLLM_Test extends AbstractTestCase {

    private File output;

    @Override
    public void tearDown() throws Exception {
        if (output != null && !output.delete()) {
            output.deleteOnExit();
        }
        super.tearDown();
    }

    @Test
    public void testCreateArchiveEntryWithFileAttributes() throws Exception {
        output = new File(dir, "file-attributes.7z");
        File inputFile = new File(dir, "testfile.txt");
        Files.write(inputFile.toPath(), "test content".getBytes());

        try (SevenZOutputFile outArchive = new SevenZOutputFile(output)) {
            SevenZArchiveEntry entry = outArchive.createArchiveEntry(inputFile, "testfile.txt");
            outArchive.putArchiveEntry(entry);
            outArchive.write(Files.readAllBytes(inputFile.toPath()));
            outArchive.closeArchiveEntry();
            outArchive.finish();
        }

        try (SevenZFile archive = new SevenZFile(output)) {
            SevenZArchiveEntry entry = archive.getNextEntry();
            assertNotNull(entry);
            assertEquals("testfile.txt", entry.getName());
            assertFalse(entry.isDirectory());

            BasicFileAttributes attributes = Files.readAttributes(inputFile.toPath(), BasicFileAttributes.class);
            assertEquals(attributes.lastModifiedTime().toMillis(), entry.getLastModifiedTime().toMillis());
            assertEquals(attributes.creationTime().toMillis(), entry.getCreationTime().toMillis());
            assertEquals(attributes.lastAccessTime().toMillis(), entry.getAccessTime().toMillis());
        }
    }

    @Test
    public void testCreateArchiveEntryWithPathAttributes() throws Exception {
        output = new File(dir, "path-attributes.7z");
        Path inputPath = Files.createFile(new File(dir, "testpath.txt").toPath());
        Files.write(inputPath, "test content".getBytes());

        try (SevenZOutputFile outArchive = new SevenZOutputFile(output)) {
            SevenZArchiveEntry entry = outArchive.createArchiveEntry(inputPath, "testpath.txt");
            outArchive.putArchiveEntry(entry);
            outArchive.write(Files.readAllBytes(inputPath));
            outArchive.closeArchiveEntry();
            outArchive.finish();
        }

        try (SevenZFile archive = new SevenZFile(output)) {
            SevenZArchiveEntry entry = archive.getNextEntry();
            assertNotNull(entry);
            assertEquals("testpath.txt", entry.getName());
            assertFalse(entry.isDirectory());

            BasicFileAttributes attributes = Files.readAttributes(inputPath, BasicFileAttributes.class);
            assertEquals(attributes.lastModifiedTime().toMillis(), entry.getLastModifiedTime().toMillis());
            assertEquals(attributes.creationTime().toMillis(), entry.getCreationTime().toMillis());
            assertEquals(attributes.lastAccessTime().toMillis(), entry.getAccessTime().toMillis());
        }
    }

    @Test
    public void testFillDatesMethod() throws Exception {
        Path inputPath = Files.createFile(new File(dir, "testdates.txt").toPath());
        Files.write(inputPath, "test content".getBytes());

        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        BasicFileAttributes attributes = Files.readAttributes(inputPath, BasicFileAttributes.class);

        try (SevenZOutputFile outArchive = new SevenZOutputFile(new SeekableInMemoryByteChannel())) {
            outArchive.fillDates(inputPath, entry);
        }

        assertEquals(attributes.lastModifiedTime().toMillis(), entry.getLastModifiedTime().toMillis());
        assertEquals(attributes.creationTime().toMillis(), entry.getCreationTime().toMillis());
        assertEquals(attributes.lastAccessTime().toMillis(), entry.getAccessTime().toMillis());
    }
}