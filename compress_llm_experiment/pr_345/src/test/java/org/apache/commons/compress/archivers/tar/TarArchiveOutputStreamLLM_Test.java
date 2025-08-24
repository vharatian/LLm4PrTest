package org.apache.commons.compress.archivers.tar;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.commons.compress.utils.TimeUtils;
import org.junit.jupiter.api.Test;

public class TarArchiveOutputStreamLLM_Test {

    @Test
    public void testFailForBigNumbersWithUnixTime() throws Exception {
        final TarArchiveEntry t = new TarArchiveEntry("foo");
        t.setSize(0100000000000L);
        t.setModTime(FileTime.fromMillis(-1000));
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (TarArchiveOutputStream tos = new TarArchiveOutputStream(bos)) {
            tos.setBigNumberMode(TarArchiveOutputStream.BIGNUMBER_POSIX);
            assertThrows(IllegalArgumentException.class, () -> tos.putArchiveEntry(t));
        }
    }

    @Test
    public void testTransferModTimeWithUnixTime() throws Exception {
        final TarArchiveEntry from = new TarArchiveEntry("from");
        from.setModTime(FileTime.fromMillis(-1000));
        final TarArchiveEntry to = new TarArchiveEntry("to");
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (TarArchiveOutputStream tos = new TarArchiveOutputStream(bos)) {
            tos.transferModTime(from, to);
        }
        final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.set(1969, 11, 31, 23, 59, 59);
        cal.set(Calendar.MILLISECOND, 0);
        assertEquals(cal.getTime(), to.getLastModifiedDate());
    }

    @Test
    public void testUnixTimeConversion() {
        final long unixTime = -1000L;
        final FileTime fileTime = TimeUtils.unixTimeToFileTime(unixTime);
        assertEquals(unixTime, TimeUtils.toUnixTime(fileTime));
    }
}