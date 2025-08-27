package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.zip.ZipEntry;
import org.junit.Test;

public class ZipArchiveEntryLLM_Test {

    @Test
    public void testDefaultValues() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test");
        assertEquals(0, entry.getInternalAttributes());
        assertEquals(0, entry.getExternalAttributes());
        assertEquals(0, entry.getAlignment());
        assertFalse(entry.isStreamContiguous());
    }

    @Test
    public void testSetInternalAttributes() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test");
        entry.setInternalAttributes(123);
        assertEquals(123, entry.getInternalAttributes());
    }

    @Test
    public void testSetExternalAttributes() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test");
        entry.setExternalAttributes(456L);
        assertEquals(456L, entry.getExternalAttributes());
    }

    @Test
    public void testSetAlignment() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test");
        entry.setAlignment(8);
        assertEquals(8, entry.getAlignment());
    }

    @Test
    public void testSetStreamContiguous() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test");
        entry.setStreamContiguous(true);
        assertTrue(entry.isStreamContiguous());
    }
}