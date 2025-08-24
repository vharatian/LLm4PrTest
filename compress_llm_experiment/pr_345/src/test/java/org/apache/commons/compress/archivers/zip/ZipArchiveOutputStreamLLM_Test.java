package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Deflater;

import static org.junit.jupiter.api.Assertions.*;

public class ZipArchiveOutputStreamLLM_Test {

    private ZipArchiveOutputStream zipOutputStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    @BeforeEach
    public void setUp() throws IOException {
        byteArrayOutputStream = new ByteArrayOutputStream();
        zipOutputStream = new ZipArchiveOutputStream(byteArrayOutputStream);
    }

    @Test
    public void testCreateCentralFileHeaderWithoutCalendarInstance() throws IOException {
        ZipArchiveEntry entry = new ZipArchiveEntry("test.txt");
        entry.setTime(System.currentTimeMillis());
        byte[] header = zipOutputStream.createCentralFileHeader(entry, zipOutputStream.getName(entry), new ZipArchiveOutputStream.EntryMetaData(0, false), false);
        assertNotNull(header);
        assertTrue(header.length > 0);
    }

    @Test
    public void testCreateLocalFileHeaderWithoutCalendarInstance() throws IOException {
        ZipArchiveEntry entry = new ZipArchiveEntry("test.txt");
        entry.setTime(System.currentTimeMillis());
        ByteBuffer name = zipOutputStream.getName(entry);
        byte[] header = zipOutputStream.createLocalFileHeader(entry, name, true, false, 0);
        assertNotNull(header);
        assertTrue(header.length > 0);
    }
}