package org.apache.commons.compress.archivers.zip;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Enumeration;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ZipFileLLM_Test {

    private ZipFile zf = null;

    @AfterEach
    public void tearDown() {
        ZipFile.closeQuietly(zf);
    }

    @Test
    public void testGetRawInputStreamWithIgnoreLocalFileHeader() throws Exception {
        final File archive = getFile("mixed.zip");
        zf = new ZipFile(archive, ZipEncodingHelper.UTF8, true, true);
        final ZipArchiveEntry entry = zf.getEntry("inflated.txt");
        assertNotNull(entry);
        assertThrows(IOException.class, () -> zf.getRawInputStream(entry));
    }

    @Test
    public void testGetRawInputStreamWithoutIgnoreLocalFileHeader() throws Exception {
        final File archive = getFile("mixed.zip");
        zf = new ZipFile(archive, ZipEncodingHelper.UTF8, true, false);
        final ZipArchiveEntry entry = zf.getEntry("inflated.txt");
        assertNotNull(entry);
        try (InputStream is = zf.getRawInputStream(entry)) {
            assertNotNull(is);
            byte[] data = IOUtils.toByteArray(is);
            assertEquals(entry.getCompressedSize(), data.length);
        }
    }

    @Test
    public void testGetInputStreamWithIgnoreLocalFileHeader() throws Exception {
        final File archive = getFile("mixed.zip");
        zf = new ZipFile(archive, ZipEncodingHelper.UTF8, true, true);
        final ZipArchiveEntry entry = zf.getEntry("inflated.txt");
        assertNotNull(entry);
        try (InputStream is = zf.getInputStream(entry)) {
            assertNotNull(is);
            byte[] data = IOUtils.toByteArray(is);
            assertEquals(entry.getSize(), data.length);
        }
    }

    @Test
    public void testGetInputStreamWithoutIgnoreLocalFileHeader() throws Exception {
        final File archive = getFile("mixed.zip");
        zf = new ZipFile(archive, ZipEncodingHelper.UTF8, true, false);
        final ZipArchiveEntry entry = zf.getEntry("inflated.txt");
        assertNotNull(entry);
        try (InputStream is = zf.getInputStream(entry)) {
            assertNotNull(is);
            byte[] data = IOUtils.toByteArray(is);
            assertEquals(entry.getSize(), data.length);
        }
    }

    @Test
    public void testGetRawInputStreamInMemory() throws Exception {
        byte[] data = null;
        try (InputStream fis = Files.newInputStream(getFile("mixed.zip").toPath())) {
            data = IOUtils.toByteArray(fis);
        }
        zf = new ZipFile(new SeekableInMemoryByteChannel(data), ZipEncodingHelper.UTF8);
        final ZipArchiveEntry entry = zf.getEntry("inflated.txt");
        assertNotNull(entry);
        try (InputStream is = zf.getRawInputStream(entry)) {
            assertNotNull(is);
            byte[] rawData = IOUtils.toByteArray(is);
            assertEquals(entry.getCompressedSize(), rawData.length);
        }
    }

    @Test
    public void testGetInputStreamInMemory() throws Exception {
        byte[] data = null;
        try (InputStream fis = Files.newInputStream(getFile("mixed.zip").toPath())) {
            data = IOUtils.toByteArray(fis);
        }
        zf = new ZipFile(new SeekableInMemoryByteChannel(data), ZipEncodingHelper.UTF8);
        final ZipArchiveEntry entry = zf.getEntry("inflated.txt");
        assertNotNull(entry);
        try (InputStream is = zf.getInputStream(entry)) {
            assertNotNull(is);
            byte[] rawData = IOUtils.toByteArray(is);
            assertEquals(entry.getSize(), rawData.length);
        }
    }
}