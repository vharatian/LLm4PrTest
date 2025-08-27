package org.apache.commons.compress.archivers.zip;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import static org.junit.Assert.*;

public class ZipArchiveOutputStreamLLM_Test {

    @Test
    public void testDefaultValues() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(baos);

        assertFalse(zos.finished);
        assertFalse(zos.hasCompressionLevelChanged);
        assertEquals(0, zos.cdOffset);
        assertEquals(0, zos.cdLength);
        assertEquals(0, zos.cdDiskNumberStart);
        assertEquals(0, zos.eocdLength);
        assertFalse(zos.fallbackToUTF8);
        assertFalse(zos.hasUsedZip64);
    }

    @Test
    public void testSetFinished() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(baos);
        zos.finish();
        assertTrue(zos.finished);
    }

    @Test
    public void testSetHasCompressionLevelChanged() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(baos);
        zos.setLevel(Deflater.BEST_COMPRESSION);
        assertTrue(zos.hasCompressionLevelChanged);
    }

    @Test
    public void testSetCdOffset() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(baos);
        zos.finish();
        assertTrue(zos.cdOffset > 0);
    }

    @Test
    public void testSetCdLength() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(baos);
        zos.finish();
        assertTrue(zos.cdLength > 0);
    }

    @Test
    public void testSetCdDiskNumberStart() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(baos);
        zos.finish();
        assertEquals(0, zos.cdDiskNumberStart);
    }

    @Test
    public void testSetEocdLength() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(baos);
        zos.finish();
        assertTrue(zos.eocdLength > 0);
    }

    @Test
    public void testSetFallbackToUTF8() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(baos);
        zos.setFallbackToUTF8(true);
        assertTrue(zos.fallbackToUTF8);
    }

    @Test
    public void testSetHasUsedZip64() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(baos);
        zos.setUseZip64(Zip64Mode.Always);
        zos.putArchiveEntry(new ZipArchiveEntry("test"));
        zos.write(new byte[1024]);
        zos.closeArchiveEntry();
        assertTrue(zos.hasUsedZip64);
    }

    @Test
    public void testCurrentEntryDefaultValues() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test");
        ZipArchiveOutputStream.CurrentEntry currentEntry = new ZipArchiveOutputStream.CurrentEntry(entry);

        assertEquals(0, currentEntry.localDataStart);
        assertEquals(0, currentEntry.dataStart);
        assertEquals(0, currentEntry.bytesRead);
        assertFalse(currentEntry.causedUseOfZip64);
    }
}