package org.apache.commons.compress.utils;

import org.junit.jupiter.api.Test;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class TimeUtilsLLM_Test {

    @Test
    public void testNtfsTimeToDate() {
        long ntfsTime = 132217567530000000L; // Example NTFS time
        Date expectedDate = new Date(1633024800000L); // Corresponding Java Date
        assertEquals(expectedDate, TimeUtils.ntfsTimeToDate(ntfsTime));
    }

    @Test
    public void testDateToNtfsTime() {
        Date date = new Date(1633024800000L); // Example Java Date
        long expectedNtfsTime = 132217567530000000L; // Corresponding NTFS time
        assertEquals(expectedNtfsTime, TimeUtils.dateToNtfsTime(date));
    }

    @Test
    public void testFileTimeToNtfsTime() {
        FileTime fileTime = FileTime.from(Instant.parse("2021-10-01T00:00:00Z")); // Example FileTime
        long expectedNtfsTime = 132217567530000000L; // Corresponding NTFS time
        assertEquals(expectedNtfsTime, TimeUtils.fileTimeToNtfsTime(fileTime));
    }

    @Test
    public void testNtfsTimeToFileTime() {
        long ntfsTime = 132217567530000000L; // Example NTFS time
        FileTime expectedFileTime = FileTime.from(Instant.parse("2021-10-01T00:00:00Z")); // Corresponding FileTime
        assertEquals(expectedFileTime, TimeUtils.ntfsTimeToFileTime(ntfsTime));
    }

    @Test
    public void testTruncateToHundredNanos() {
        FileTime fileTime = FileTime.from(Instant.parse("2021-10-01T00:00:00.123456789Z")); // Example FileTime
        FileTime expectedFileTime = FileTime.from(Instant.parse("2021-10-01T00:00:00.123456700Z")); // Truncated FileTime
        assertEquals(expectedFileTime, TimeUtils.truncateToHundredNanos(fileTime));
    }

    @Test
    public void testDateToFileTime() {
        Date date = new Date(1633024800000L); // Example Java Date
        FileTime expectedFileTime = FileTime.fromMillis(1633024800000L); // Corresponding FileTime
        assertEquals(expectedFileTime, TimeUtils.dateToFileTime(date));
    }

    @Test
    public void testDateToFileTimeNull() {
        assertNull(TimeUtils.dateToFileTime(null));
    }

    @Test
    public void testFileTimeToDate() {
        FileTime fileTime = FileTime.fromMillis(1633024800000L); // Example FileTime
        Date expectedDate = new Date(1633024800000L); // Corresponding Java Date
        assertEquals(expectedDate, TimeUtils.fileTimeToDate(fileTime));
    }

    @Test
    public void testFileTimeToDateNull() {
        assertNull(TimeUtils.fileTimeToDate(null));
    }
}