package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ZipArchiveInputStreamLLM_Test {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /**
     * Test to ensure that the final int read change does not affect the functionality.
     */
    @Test
    public void testReadFunctionalityWithFinalInt() throws Exception {
        try (ZipArchiveInputStream in = new ZipArchiveInputStream(new FileInputStream(getFile("test.zip")))) {
            ZipArchiveEntry entry = in.getNextZipEntry();
            assertNotNull(entry);
            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer, 0, buffer.length);
            assertTrue(bytesRead > 0);
        }
    }

    /**
     * Test to ensure that the final int read change does not affect reading stored entries.
     */
    @Test
    public void testReadStoredEntriesWithFinalInt() throws Exception {
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

    /**
     * Test to ensure that the final int read change does not affect reading deflated entries.
     */
    @Test
    public void testReadDeflatedEntriesWithFinalInt() throws Exception {
        try (FileInputStream fs = new FileInputStream(getFile("bla.zip"));
             ZipArchiveInputStream archive = new ZipArchiveInputStream(fs)) {
            ZipArchiveEntry e = archive.getNextZipEntry();
            assertNotNull(e);
            assertEquals("test1.xml", e.getName());
            byte[] data = IOUtils.toByteArray(archive);
            assertTrue(data.length > 0);
            e = archive.getNextZipEntry();
            assertNotNull(e);
            assertEquals("test2.xml", e.getName());
            data = IOUtils.toByteArray(archive);
            assertTrue(data.length > 0);
            assertNull(archive.getNextEntry());
        }
    }
}