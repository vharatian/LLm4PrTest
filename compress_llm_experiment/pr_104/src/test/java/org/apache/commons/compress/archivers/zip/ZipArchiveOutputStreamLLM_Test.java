package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZipArchiveOutputStreamLLM_Test {

    @Test
    public void testIsTooLargeForZip32() {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        entry.setSize(ZipArchiveOutputStream.ZIP64_MAGIC - 1);
        entry.setCompressedSize(ZipArchiveOutputStream.ZIP64_MAGIC - 1);
        ZipArchiveOutputStream zipOutputStream = new ZipArchiveOutputStream(System.out);

        assertFalse(zipOutputStream.isTooLargeForZip32(entry));

        entry.setSize(ZipArchiveOutputStream.ZIP64_MAGIC);
        assertTrue(zipOutputStream.isTooLargeForZip32(entry));

        entry.setSize(ZipArchiveOutputStream.ZIP64_MAGIC - 1);
        entry.setCompressedSize(ZipArchiveOutputStream.ZIP64_MAGIC);
        assertTrue(zipOutputStream.isTooLargeForZip32(entry));
    }

    @Test
    public void testIsZip64Required() {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        entry.setSize(ZipArchiveOutputStream.ZIP64_MAGIC - 1);
        entry.setCompressedSize(ZipArchiveOutputStream.ZIP64_MAGIC - 1);
        ZipArchiveOutputStream zipOutputStream = new ZipArchiveOutputStream(System.out);

        assertFalse(zipOutputStream.isZip64Required(entry, Zip64Mode.Never));
        assertTrue(zipOutputStream.isZip64Required(entry, Zip64Mode.Always));

        entry.setSize(ZipArchiveOutputStream.ZIP64_MAGIC);
        assertTrue(zipOutputStream.isZip64Required(entry, Zip64Mode.AsNeeded));
    }
}