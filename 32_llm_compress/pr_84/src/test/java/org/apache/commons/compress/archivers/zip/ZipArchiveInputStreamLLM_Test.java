package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipException;

import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class ZipArchiveInputStreamLLM_Test {

    @Test
    public void testSkipSplitSigFalse() throws IOException {
        try (FileInputStream fs = new FileInputStream(getFile("split-zip.zip"));
             ZipArchiveInputStream archive = new ZipArchiveInputStream(fs, "UTF-8", true, true, false)) {
            ZipArchiveEntry e = archive.getNextZipEntry();
            assertNotNull(e);
            assertEquals("test1.txt", e.getName());
            byte[] data = IOUtils.toByteArray(archive);
            assertEquals(5, data.length);
        }
    }

    @Test
    public void testSkipSplitSigTrue() throws IOException {
        try (FileInputStream fs = new FileInputStream(getFile("split-zip.zip"));
             ZipArchiveInputStream archive = new ZipArchiveInputStream(fs, "UTF-8", true, true, true)) {
            ZipArchiveEntry e = archive.getNextZipEntry();
            assertNotNull(e);
            assertEquals("test1.txt", e.getName());
            byte[] data = IOUtils.toByteArray(archive);
            assertEquals(5, data.length);
        }
    }

    @Test
    public void testSkipSplitSigThrowsException() throws IOException {
        try (FileInputStream fs = new FileInputStream(getFile("split-zip-invalid.zip"));
             ZipArchiveInputStream archive = new ZipArchiveInputStream(fs, "UTF-8", true, true, false)) {
            archive.getNextZipEntry();
            fail("Expected ZipException");
        } catch (ZipException e) {
            assertTrue(e.getMessage().contains("Unexpected record signature"));
        }
    }
}