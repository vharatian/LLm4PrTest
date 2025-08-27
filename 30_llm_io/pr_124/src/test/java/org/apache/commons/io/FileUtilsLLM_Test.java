package org.apache.commons.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    private File testFile;
    private File referenceFile;

    @BeforeEach
    public void setUp() throws IOException {
        testFile = File.createTempFile("testFile", ".txt");
        referenceFile = File.createTempFile("referenceFile", ".txt");
    }

    @Test
    public void testIsFileNewerWithInstant() {
        Instant now = Instant.now();
        assertTrue(FileUtils.isFileNewer(testFile, now.minusSeconds(10)));
        assertFalse(FileUtils.isFileNewer(testFile, now.plusSeconds(10)));
    }

    @Test
    public void testIsFileNewerWithChronoZonedDateTime() {
        ChronoZonedDateTime<?> now = ChronoZonedDateTime.now();
        assertTrue(FileUtils.isFileNewer(testFile, now.minusSeconds(10)));
        assertFalse(FileUtils.isFileNewer(testFile, now.plusSeconds(10)));
    }

    @Test
    public void testIsFileNewerWithChronoLocalDateTime() {
        ChronoLocalDateTime<?> now = ChronoLocalDateTime.now();
        assertTrue(FileUtils.isFileNewer(testFile, now.minusSeconds(10)));
        assertFalse(FileUtils.isFileNewer(testFile, now.plusSeconds(10)));
    }

    @Test
    public void testIsFileNewerWithChronoLocalDateTimeAndZoneId() {
        ChronoLocalDateTime<?> now = ChronoLocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        assertTrue(FileUtils.isFileNewer(testFile, now.minusSeconds(10), zoneId));
        assertFalse(FileUtils.isFileNewer(testFile, now.plusSeconds(10), zoneId));
    }

    @Test
    public void testIsFileNewerWithChronoLocalDate() {
        ChronoLocalDate today = ChronoLocalDate.now();
        assertTrue(FileUtils.isFileNewer(testFile, today.minusDays(1)));
        assertFalse(FileUtils.isFileNewer(testFile, today.plusDays(1)));
    }

    @Test
    public void testIsFileNewerWithChronoLocalDateAndLocalTime() {
        ChronoLocalDate today = ChronoLocalDate.now();
        LocalTime now = LocalTime.now();
        assertTrue(FileUtils.isFileNewer(testFile, today.minusDays(1), now));
        assertFalse(FileUtils.isFileNewer(testFile, today.plusDays(1), now));
    }

    @Test
    public void testIsFileOlderWithInstant() {
        Instant now = Instant.now();
        assertFalse(FileUtils.isFileOlder(testFile, now.minusSeconds(10)));
        assertTrue(FileUtils.isFileOlder(testFile, now.plusSeconds(10)));
    }

    @Test
    public void testIsFileOlderWithChronoZonedDateTime() {
        ChronoZonedDateTime<?> now = ChronoZonedDateTime.now();
        assertFalse(FileUtils.isFileOlder(testFile, now.minusSeconds(10)));
        assertTrue(FileUtils.isFileOlder(testFile, now.plusSeconds(10)));
    }

    @Test
    public void testIsFileOlderWithChronoLocalDateTime() {
        ChronoLocalDateTime<?> now = ChronoLocalDateTime.now();
        assertFalse(FileUtils.isFileOlder(testFile, now.minusSeconds(10)));
        assertTrue(FileUtils.isFileOlder(testFile, now.plusSeconds(10)));
    }

    @Test
    public void testIsFileOlderWithChronoLocalDateTimeAndZoneId() {
        ChronoLocalDateTime<?> now = ChronoLocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        assertFalse(FileUtils.isFileOlder(testFile, now.minusSeconds(10), zoneId));
        assertTrue(FileUtils.isFileOlder(testFile, now.plusSeconds(10), zoneId));
    }

    @Test
    public void testIsFileOlderWithChronoLocalDate() {
        ChronoLocalDate today = ChronoLocalDate.now();
        assertFalse(FileUtils.isFileOlder(testFile, today.minusDays(1)));
        assertTrue(FileUtils.isFileOlder(testFile, today.plusDays(1)));
    }

    @Test
    public void testIsFileOlderWithChronoLocalDateAndLocalTime() {
        ChronoLocalDate today = ChronoLocalDate.now();
        LocalTime now = LocalTime.now();
        assertFalse(FileUtils.isFileOlder(testFile, today.minusDays(1), now));
        assertTrue(FileUtils.isFileOlder(testFile, today.plusDays(1), now));
    }
}