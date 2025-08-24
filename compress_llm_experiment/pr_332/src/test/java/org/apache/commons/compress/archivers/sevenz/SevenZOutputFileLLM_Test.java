package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;

import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;

public class SevenZOutputFileLLM_Test extends AbstractTestCase {

    private File output;

    @Override
    public void tearDown() throws Exception {
        if (output != null && !output.delete()) {
            output.deleteOnExit();
        }
        super.tearDown();
    }

    /**
     * Test creating a SevenZOutputFile with a password.
     */
    @Test
    public void testCreateArchiveWithPassword() throws Exception {
        output = new File(dir, "password-protected.7z");
        char[] password = "password".toCharArray();
        try (SevenZOutputFile outArchive = new SevenZOutputFile(output, password)) {
            SevenZArchiveEntry entry = new SevenZArchiveEntry();
            entry.setName("test.txt");
            outArchive.putArchiveEntry(entry);
            outArchive.write(new byte[] { 'A', 'B', 'C' });
            outArchive.closeArchiveEntry();
            outArchive.finish();
        }

        try (SevenZFile archive = new SevenZFile(output, password)) {
            SevenZArchiveEntry entry = archive.getNextEntry();
            assertNotNull(entry);
            assertEquals("test.txt", entry.getName());
            assertFalse(entry.isDirectory());
            assertEquals(3, entry.getSize());
            byte[] content = new byte[3];
            assertEquals(3, archive.read(content));
            assertEquals('A', content[0]);
            assertEquals('B', content[1]);
            assertEquals('C', content[2]);
            assertEquals(-1, archive.read());
        }
    }

    /**
     * Test creating a SevenZOutputFile without a password.
     */
    @Test
    public void testCreateArchiveWithoutPassword() throws Exception {
        output = new File(dir, "no-password.7z");
        try (SevenZOutputFile outArchive = new SevenZOutputFile(output)) {
            SevenZArchiveEntry entry = new SevenZArchiveEntry();
            entry.setName("test.txt");
            outArchive.putArchiveEntry(entry);
            outArchive.write(new byte[] { 'A', 'B', 'C' });
            outArchive.closeArchiveEntry();
            outArchive.finish();
        }

        try (SevenZFile archive = new SevenZFile(output)) {
            SevenZArchiveEntry entry = archive.getNextEntry();
            assertNotNull(entry);
            assertEquals("test.txt", entry.getName());
            assertFalse(entry.isDirectory());
            assertEquals(3, entry.getSize());
            byte[] content = new byte[3];
            assertEquals(3, archive.read(content));
            assertEquals('A', content[0]);
            assertEquals('B', content[1]);
            assertEquals('C', content[2]);
            assertEquals(-1, archive.read());
        }
    }

    /**
     * Test creating an in-memory SevenZOutputFile with a password.
     */
    @Test
    public void testCreateInMemoryArchiveWithPassword() throws Exception {
        char[] password = "password".toCharArray();
        SeekableInMemoryByteChannel output = new SeekableInMemoryByteChannel();
        try (SevenZOutputFile outArchive = new SevenZOutputFile(output, password)) {
            SevenZArchiveEntry entry = new SevenZArchiveEntry();
            entry.setName("test.txt");
            outArchive.putArchiveEntry(entry);
            outArchive.write(new byte[] { 'A', 'B', 'C' });
            outArchive.closeArchiveEntry();
            outArchive.finish();
        }

        try (SevenZFile archive = new SevenZFile(new SeekableInMemoryByteChannel(output.array()), password)) {
            SevenZArchiveEntry entry = archive.getNextEntry();
            assertNotNull(entry);
            assertEquals("test.txt", entry.getName());
            assertFalse(entry.isDirectory());
            assertEquals(3, entry.getSize());
            byte[] content = new byte[3];
            assertEquals(3, archive.read(content));
            assertEquals('A', content[0]);
            assertEquals('B', content[1]);
            assertEquals('C', content[2]);
            assertEquals(-1, archive.read());
        }
    }

    /**
     * Test creating an in-memory SevenZOutputFile without a password.
     */
    @Test
    public void testCreateInMemoryArchiveWithoutPassword() throws Exception {
        SeekableInMemoryByteChannel output = new SeekableInMemoryByteChannel();
        try (SevenZOutputFile outArchive = new SevenZOutputFile(output)) {
            SevenZArchiveEntry entry = new SevenZArchiveEntry();
            entry.setName("test.txt");
            outArchive.putArchiveEntry(entry);
            outArchive.write(new byte[] { 'A', 'B', 'C' });
            outArchive.closeArchiveEntry();
            outArchive.finish();
        }

        try (SevenZFile archive = new SevenZFile(new SeekableInMemoryByteChannel(output.array()))) {
            SevenZArchiveEntry entry = archive.getNextEntry();
            assertNotNull(entry);
            assertEquals("test.txt", entry.getName());
            assertFalse(entry.isDirectory());
            assertEquals(3, entry.getSize());
            byte[] content = new byte[3];
            assertEquals(3, archive.read(content));
            assertEquals('A', content[0]);
            assertEquals('B', content[1]);
            assertEquals('C', content[2]);
            assertEquals(-1, archive.read());
        }
    }
}