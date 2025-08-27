package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import org.junit.Test;

public class TarArchiveEntryLLM_Test {

    @Test
    public void testGetModTime() {
        // Create a TarArchiveEntry instance
        TarArchiveEntry entry = new TarArchiveEntry("testEntry");

        // Set modification time
        Date now = new Date();
        entry.setModTime(now);

        // Retrieve modification time
        Date modTime = entry.getModTime();

        // Assert that the modification time is correctly retrieved
        assertNotNull(modTime);
        assertEquals(now.getTime() / 1000 * 1000, modTime.getTime());
    }

    @Test
    public void testGetModTimeWithDifferentDate() {
        // Create a TarArchiveEntry instance
        TarArchiveEntry entry = new TarArchiveEntry("testEntry");

        // Set modification time to a specific date
        Date specificDate = new Date(1633036800000L); // 2021-10-01 00:00:00 UTC
        entry.setModTime(specificDate);

        // Retrieve modification time
        Date modTime = entry.getModTime();

        // Assert that the modification time is correctly retrieved
        assertNotNull(modTime);
        assertEquals(specificDate.getTime() / 1000 * 1000, modTime.getTime());
    }

    @Test
    public void testGetModTimeAfterMultipleSets() {
        // Create a TarArchiveEntry instance
        TarArchiveEntry entry = new TarArchiveEntry("testEntry");

        // Set modification time to a specific date
        Date firstDate = new Date(1633036800000L); // 2021-10-01 00:00:00 UTC
        entry.setModTime(firstDate);

        // Set modification time to another specific date
        Date secondDate = new Date(1633123200000L); // 2021-10-02 00:00:00 UTC
        entry.setModTime(secondDate);

        // Retrieve modification time
        Date modTime = entry.getModTime();

        // Assert that the modification time is correctly retrieved
        assertNotNull(modTime);
        assertEquals(secondDate.getTime() / 1000 * 1000, modTime.getTime());
    }
}