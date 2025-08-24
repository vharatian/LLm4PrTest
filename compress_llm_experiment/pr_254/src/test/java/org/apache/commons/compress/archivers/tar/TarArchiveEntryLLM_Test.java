package org.apache.commons.compress.archivers.tar;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.apache.commons.compress.AbstractTestCase.getPath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.CharsetNames;
import org.junit.jupiter.api.Test;

public class TarArchiveEntryLLM_Test implements TarConstants {

    private static final String OS = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
    private static final String ROOT = OS.startsWith("windows") || OS.startsWith("netware") ? "C:\\" : "/";

    @Test
    public void testSetAndGetFileTimes() {
        TarArchiveEntry entry = new TarArchiveEntry("test");

        FileTime mTime = FileTime.from(Instant.now());
        FileTime aTime = FileTime.from(Instant.now().minusSeconds(3600));
        FileTime cTime = FileTime.from(Instant.now().minusSeconds(7200));
        FileTime birthTime = FileTime.from(Instant.now().minusSeconds(10800));

        entry.setLastModifiedTime(mTime);
        entry.setLastAccessTime(aTime);
        entry.setStatusChangeTime(cTime);
        entry.setCreationTime(birthTime);

        assertEquals(mTime, entry.getLastModifiedTime());
        assertEquals(aTime, entry.getLastAccessTime());
        assertEquals(cTime, entry.getStatusChangeTime());
        assertEquals(birthTime, entry.getCreationTime());
    }

    @Test
    public void testParseTarHeaderWithGlobalPaxHeaders() throws IOException {
        byte[] headerBuf = new byte[512];
        Map<String, String> globalPaxHeaders = Collections.singletonMap("SCHILY.archtype", "xustar");

        TarArchiveEntry entry = new TarArchiveEntry(globalPaxHeaders, headerBuf, ZipEncodingHelper.getZipEncoding(CharsetNames.ISO_8859_1), true);

        assertNotNull(entry);
    }

    @Test
    public void testProcessPaxHeaderWithNewAttributes() throws IOException {
        TarArchiveEntry entry = new TarArchiveEntry("test");

        String mtime = "1625097600.123456789"; // 2021-07-01T00:00:00.123456789Z
        String atime = "1625094000.123456789"; // 2021-06-30T23:00:00.123456789Z
        String ctime = "1625090400.123456789"; // 2021-06-30T22:00:00.123456789Z
        String birthtime = "1625086800.123456789"; // 2021-06-30T21:00:00.123456789Z

        entry.processPaxHeader("mtime", mtime);
        entry.processPaxHeader("atime", atime);
        entry.processPaxHeader("ctime", ctime);
        entry.processPaxHeader("LIBARCHIVE.creationtime", birthtime);

        assertEquals(FileTime.from(Instant.parse("2021-07-01T00:00:00.123456789Z")), entry.getLastModifiedTime());
        assertEquals(FileTime.from(Instant.parse("2021-06-30T23:00:00.123456789Z")), entry.getLastAccessTime());
        assertEquals(FileTime.from(Instant.parse("2021-06-30T22:00:00.123456789Z")), entry.getStatusChangeTime());
        assertEquals(FileTime.from(Instant.parse("2021-06-30T21:00:00.123456789Z")), entry.getCreationTime());
    }

    @Test
    public void testWriteEntryHeaderWithFileTimes() throws IOException {
        TarArchiveEntry entry = new TarArchiveEntry("test");

        FileTime mTime = FileTime.from(Instant.now());
        FileTime aTime = FileTime.from(Instant.now().minusSeconds(3600));
        FileTime cTime = FileTime.from(Instant.now().minusSeconds(7200));

        entry.setLastModifiedTime(mTime);
        entry.setLastAccessTime(aTime);
        entry.setStatusChangeTime(cTime);

        byte[] outbuf = new byte[512];
        entry.writeEntryHeader(outbuf, ZipEncodingHelper.getZipEncoding(CharsetNames.ISO_8859_1), true);

        // Verify that the header contains the correct times
        long mTimeSeconds = mTime.to(TimeUnit.SECONDS);
        long aTimeSeconds = aTime.to(TimeUnit.SECONDS);
        long cTimeSeconds = cTime.to(TimeUnit.SECONDS);

        assertEquals(mTimeSeconds, TarUtils.parseOctalOrBinary(outbuf, 136, 12));
        assertEquals(aTimeSeconds, TarUtils.parseOctalOrBinary(outbuf, 476, 12));
        assertEquals(cTimeSeconds, TarUtils.parseOctalOrBinary(outbuf, 488, 12));
    }

    @Test
    public void testEvaluateTypeWithGlobalPaxHeaders() {
        byte[] headerBuf = new byte[512];
        Map<String, String> globalPaxHeaders = Collections.singletonMap("SCHILY.archtype", "xustar");

        TarArchiveEntry entry = new TarArchiveEntry("test");
        int type = entry.evaluateType(globalPaxHeaders, headerBuf);

        assertEquals(TarArchiveEntry.FORMAT_XSTAR, type);
    }
}