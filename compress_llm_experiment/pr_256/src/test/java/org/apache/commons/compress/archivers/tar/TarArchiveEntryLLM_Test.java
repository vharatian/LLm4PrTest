package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.compress.utils.TimeUtils;
import org.junit.jupiter.api.Test;

public class TarArchiveEntryLLM_Test {

    @Test
    public void testDeprecatedMillisPerSecond() {
        assertEquals(1000, TarArchiveEntry.MILLIS_PER_SECOND);
    }

    @Test
    public void testGetModTimeUsesTimeUtils() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        Date now = new Date();
        entry.setModTime(now);
        assertEquals(TimeUtils.fileTimeToDate(entry.getLastModifiedTime()), entry.getModTime());
    }

    @Test
    public void testSetModTimeUsesTimeUtils() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        Date now = new Date();
        entry.setModTime(now);
        assertEquals(TimeUtils.dateToFileTime(now), entry.getLastModifiedTime());
    }
}