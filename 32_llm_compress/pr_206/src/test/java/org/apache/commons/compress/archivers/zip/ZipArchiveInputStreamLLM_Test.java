package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class ZipArchiveInputStreamLLM_Test {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAllowStoredEntriesWithDataDescriptorIsFinal() throws Exception {
        // Create a ZipArchiveInputStream with allowStoredEntriesWithDataDescriptor set to true
        try (InputStream fs = Files.newInputStream(getFile("bla-stored-dd.zip").toPath());
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
    public void testRejectsStoredEntriesWithDataDescriptorWhenFinalIsFalse() throws Exception {
        // Create a ZipArchiveInputStream with allowStoredEntriesWithDataDescriptor set to false
        try (InputStream fs = Files.newInputStream(getFile("bla-stored-dd.zip").toPath());
             ZipArchiveInputStream archive = new ZipArchiveInputStream(fs, "UTF-8", true, false)) {
            final ZipArchiveEntry e = archive.getNextZipEntry();
            assertNotNull(e);
            assertEquals("test1.xml", e.getName());
            assertEquals(-1, e.getCompressedSize());
            assertEquals(-1, e.getSize());
            thrown.expect(UnsupportedZipFeatureException.class);
            thrown.expectMessage("Unsupported feature data descriptor used in entry test1.xml");
            final byte[] data = IOUtils.toByteArray(archive);
        }
    }
}