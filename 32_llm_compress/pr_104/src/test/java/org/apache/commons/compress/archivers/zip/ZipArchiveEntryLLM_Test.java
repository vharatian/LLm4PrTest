package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.*;
import org.junit.Test;

public class ZipArchiveEntryLLM_Test {

    @Test
    public void testHashCodePerformance() {
        ZipArchiveEntry entry1 = new ZipArchiveEntry("entry1");
        ZipArchiveEntry entry2 = new ZipArchiveEntry("entry2");
        ZipArchiveEntry entry3 = new ZipArchiveEntry("entry1");

        // Ensure hashCode is consistent with equals
        assertEquals(entry1.hashCode(), entry3.hashCode());
        assertNotEquals(entry1.hashCode(), entry2.hashCode());

        // Ensure hashCode does not degrade performance
        long startTime = System.nanoTime();
        int hashCode1 = entry1.hashCode();
        int hashCode2 = entry2.hashCode();
        int hashCode3 = entry3.hashCode();
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        assertTrue("hashCode method should be performant", duration < 1000000); // 1 millisecond
    }

    @Test
    public void testExtraFieldParsingBehavior() {
        ZipArchiveEntry.ExtraFieldParsingMode mode = ZipArchiveEntry.ExtraFieldParsingMode.BEST_EFFORT;
        assertNotNull(mode);

        ZipExtraField field = new UnrecognizedExtraField();
        byte[] data = new byte[] {0, 1, 2, 3};
        ZipExtraField result = mode.fill(field, data, 0, data.length, true);

        assertNotNull(result);
        assertTrue(result instanceof UnrecognizedExtraField);
    }
}