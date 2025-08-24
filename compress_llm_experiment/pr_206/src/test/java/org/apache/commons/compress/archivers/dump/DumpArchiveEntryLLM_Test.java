package org.apache.commons.compress.archivers.dump;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class DumpArchiveEntryLLM_Test {

    @Test
    public void testEqualsWithDifferentIno() {
        DumpArchiveEntry entry1 = new DumpArchiveEntry("foo", "bar", 1, DumpArchiveEntry.TYPE.FILE);
        DumpArchiveEntry entry2 = new DumpArchiveEntry("foo", "bar", 2, DumpArchiveEntry.TYPE.FILE);
        assertFalse(entry1.equals(entry2));
    }

    @Test
    public void testEqualsWithSameIno() {
        DumpArchiveEntry entry1 = new DumpArchiveEntry("foo", "bar", 1, DumpArchiveEntry.TYPE.FILE);
        DumpArchiveEntry entry2 = new DumpArchiveEntry("foo", "bar", 1, DumpArchiveEntry.TYPE.FILE);
        assertTrue(entry1.equals(entry2));
    }

    @Test
    public void testEqualsWithNull() {
        DumpArchiveEntry entry1 = new DumpArchiveEntry("foo", "bar", 1, DumpArchiveEntry.TYPE.FILE);
        assertFalse(entry1.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        DumpArchiveEntry entry1 = new DumpArchiveEntry("foo", "bar", 1, DumpArchiveEntry.TYPE.FILE);
        String notAnEntry = "Not an entry";
        assertFalse(entry1.equals(notAnEntry));
    }
}