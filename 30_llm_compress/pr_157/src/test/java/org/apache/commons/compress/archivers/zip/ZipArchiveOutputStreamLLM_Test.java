package org.apache.commons.compress.archivers.zip;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ZipArchiveOutputStreamLLM_Test {

    @Test
    public void testPutArchiveEntryWithPhased() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(baos);
        ZipArchiveEntry entry = new ZipArchiveEntry("test.txt");
        entry.setSize(100);
        entry.setCompressedSize(100);
        entry.setCrc(12345L);

        zos.putArchiveEntry(entry, true);

        // Ensure that the sizes are correctly set
        assertEquals(100, entry.getSize());
        assertEquals(100, entry.getCompressedSize());
    }

    @Test
    public void testCreateCentralFileHeaderExtraLength() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(baos);
        ZipArchiveEntry entry = new ZipArchiveEntry("test.txt");
        entry.setSize(100);
        entry.setCompressedSize(100);
        entry.setCrc(12345L);
        entry.setComment("Test comment");

        zos.putArchiveEntry(entry);
        zos.closeArchiveEntry();

        byte[] centralFileHeader = zos.createCentralFileHeader(entry);
        assertNotNull(centralFileHeader);

        // Ensure that the extra length is correctly set
        int extraLength = entry.getCentralDirectoryExtra().length;
        assertEquals(extraLength, centralFileHeader[ZipArchiveOutputStream.CFH_EXTRA_LENGTH_OFFSET]);
    }
}