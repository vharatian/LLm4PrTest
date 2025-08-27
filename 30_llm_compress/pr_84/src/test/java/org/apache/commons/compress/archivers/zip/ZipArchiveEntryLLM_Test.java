package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.*;
import org.junit.Test;

public class ZipArchiveEntryLLM_Test {

    @Test
    public void testDiskNumberStart() {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");

        // Test default value
        assertEquals(0, entry.getDiskNumberStart());

        // Test setting and getting disk number start
        entry.setDiskNumberStart(5);
        assertEquals(5, entry.getDiskNumberStart());

        entry.setDiskNumberStart(10);
        assertEquals(10, entry.getDiskNumberStart());
    }
}