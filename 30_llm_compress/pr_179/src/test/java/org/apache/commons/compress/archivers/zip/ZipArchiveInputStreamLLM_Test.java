package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class ZipArchiveInputStreamLLM_Test {

    @Test
    public void testInitializationDefaults() throws IOException {
        try (ZipArchiveInputStream zip = new ZipArchiveInputStream(new FileInputStream(getFile("bla.zip")))) {
            // Test that the default values are correctly set
            assertNull(zip.current);
            assertEquals(false, zip.closed);
            assertEquals(false, zip.hitCentralDirectory);
            assertNull(zip.lastStoredEntry);
            assertEquals(false, zip.allowStoredEntriesWithDataDescriptor);
            assertEquals(0, zip.uncompressedCount);
            assertEquals(0, zip.entriesRead);
        }
    }

    @Test
    public void testProperInitializationWithParameters() throws IOException {
        try (ZipArchiveInputStream zip = new ZipArchiveInputStream(new FileInputStream(getFile("bla.zip")), "UTF-8", true, true, true)) {
            // Test that the values are correctly set when parameters are provided
            assertNull(zip.current);
            assertEquals(false, zip.closed);
            assertEquals(false, zip.hitCentralDirectory);
            assertNull(zip.lastStoredEntry);
            assertEquals(true, zip.allowStoredEntriesWithDataDescriptor);
            assertEquals(0, zip.uncompressedCount);
            assertEquals(0, zip.entriesRead);
        }
    }

    @Test
    public void testReadStoredEntryWithDataDescriptor() throws IOException {
        try (FileInputStream fs = new FileInputStream(getFile("bla-stored-dd.zip"));
             ZipArchiveInputStream archive = new ZipArchiveInputStream(fs, "UTF-8", true, true)) {
            final ZipArchiveEntry e = archive.getNextZipEntry();
            assertNotNull(e);
            assertEquals("test1.xml", e.getName());
            assertEquals(-1, e.getCompressedSize());
            assertEquals(-1, e.getSize());
            final byte[] data = IOUtils.toByteArray(archive);
            assertEquals(610, data.length);
            assertEquals(610, e.getCompressedSize());
            assertEquals(610, e.getSize());
        }
    }

    @Test
    public void testReadStoredEntryWithDataDescriptorWithoutSignature() throws IOException {
        try (FileInputStream fs = new FileInputStream(getFile("bla-stored-dd-nosig.zip"));
             ZipArchiveInputStream archive = new ZipArchiveInputStream(fs, "UTF-8", true, true)) {
            final ZipArchiveEntry e = archive.getNextZipEntry();
            assertNotNull(e);
            assertEquals("test1.xml", e.getName());
            assertEquals(-1, e.getCompressedSize());
            assertEquals(-1, e.getSize());
            final byte[] data = IOUtils.toByteArray(archive);
            assertEquals(610, data.length);
            assertEquals(610, e.getCompressedSize());
            assertEquals(610, e.getSize());
        }
    }

    @Test
    public void testProperlyReadsStoredEntries() throws IOException {
        try (FileInputStream fs = new FileInputStream(getFile("bla-stored.zip"));
             ZipArchiveInputStream archive = new ZipArchiveInputStream(fs)) {
            ZipArchiveEntry e = archive.getNextZipEntry();
            assertNotNull(e);
            assertEquals("test1.xml", e.getName());
            assertEquals(610, e.getCompressedSize());
            assertEquals(610, e.getSize());
            byte[] data = IOUtils.toByteArray(archive);
            assertEquals(610, data.length);
            e = archive.getNextZipEntry();
            assertNotNull(e);
            assertEquals("test2.xml", e.getName());
            assertEquals(82, e.getCompressedSize());
            assertEquals(82, e.getSize());
            data = IOUtils.toByteArray(archive);
            assertEquals(82, data.length);
            assertNull(archive.getNextEntry());
        }
    }
}