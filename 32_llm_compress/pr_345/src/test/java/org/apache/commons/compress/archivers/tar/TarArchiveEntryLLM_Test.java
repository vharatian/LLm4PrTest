package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.utils.TimeUtils;
import org.junit.jupiter.api.Test;

public class TarArchiveEntryLLM_Test {

    @Test
    public void testFileTimeFromOptionalSeconds() {
        // Test with valid seconds
        long seconds = 1647221103L;
        FileTime expected = TimeUtils.unixTimeToFileTime(seconds);
        FileTime actual = TarArchiveEntry.fileTimeFromOptionalSeconds(seconds);
        assertEquals(expected, actual);

        // Test with zero seconds
        seconds = 0L;
        actual = TarArchiveEntry.fileTimeFromOptionalSeconds(seconds);
        assertEquals(null, actual);

        // Test with negative seconds
        seconds = -1L;
        actual = TarArchiveEntry.fileTimeFromOptionalSeconds(seconds);
        assertEquals(null, actual);
    }

    @Test
    public void testParseTarHeaderUnwrapped() throws IOException {
        byte[] header = new byte[512];
        header[TarConstants.MODTIMELEN] = 0x30; // '0'
        TarArchiveEntry entry = new TarArchiveEntry(header);
        long expectedTime = 0L;
        FileTime expectedFileTime = TimeUtils.unixTimeToFileTime(expectedTime);
        assertEquals(expectedFileTime, entry.getLastModifiedTime());
    }

    @Test
    public void testWriteEntryHeaderField() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        byte[] outbuf = new byte[512];
        long value = 1647221103L;
        int offset = 0;
        int length = TarConstants.MODTIMELEN;
        boolean starMode = false;

        int newOffset = entry.writeEntryHeaderField(value, outbuf, offset, length, starMode);
        long writtenValue = TimeUtils.toUnixTime(FileTime.from(Instant.ofEpochSecond(value)));
        assertEquals(writtenValue, TarUtils.parseOctalOrBinary(outbuf, offset, length));
    }

    @Test
    public void testWriteEntryHeaderOptionalTimeField() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        byte[] outbuf = new byte[512];
        FileTime time = FileTime.from(Instant.parse("2022-03-14T01:25:03Z"));
        int offset = 0;
        int fieldLength = TarConstants.MODTIMELEN;

        int newOffset = entry.writeEntryHeaderOptionalTimeField(time, offset, outbuf, fieldLength);
        long writtenValue = TimeUtils.toUnixTime(time);
        assertEquals(writtenValue, TarUtils.parseOctalOrBinary(outbuf, offset, fieldLength));
    }
}