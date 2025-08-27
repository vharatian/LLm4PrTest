package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

public class ZipArchiveEntryLLM_Test {

    @Test
    public void testIsDirectory() {
        ZipArchiveEntry entry1 = new ZipArchiveEntry("folder/");
        assertTrue(entry1.isDirectory());

        ZipArchiveEntry entry2 = new ZipArchiveEntry("file.txt");
        assertTrue(!entry2.isDirectory());
    }

    @Test
    public void testHashCode() {
        ZipArchiveEntry entry1 = new ZipArchiveEntry("folder/");
        ZipArchiveEntry entry2 = new ZipArchiveEntry("folder/");
        assertEquals(entry1.hashCode(), entry2.hashCode());

        ZipArchiveEntry entry3 = new ZipArchiveEntry("file.txt");
        assertTrue(entry1.hashCode() != entry3.hashCode());
    }
}