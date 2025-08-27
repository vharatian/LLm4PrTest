package org.apache.commons.compress.archivers.tar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.compress.utils.ParsingUtils;
import org.junit.jupiter.api.Test;

public class TarArchiveEntryLLM_Test {

    @Test
    public void testFillGNUSparse0xDataWithInvalidSize() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        Map<String, String> headers = Collections.singletonMap(TarGnuSparseKeys.SIZE, "invalid");
        assertThrows(IOException.class, () -> entry.fillGNUSparse0xData(headers));
    }

    @Test
    public void testFillGNUSparse1xDataWithInvalidRealSize() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        Map<String, String> headers = Collections.singletonMap(TarGnuSparseKeys.REALSIZE, "invalid");
        assertThrows(IOException.class, () -> entry.fillGNUSparse1xData(headers));
    }

    @Test
    public void testFillStarSparseDataWithInvalidRealSize() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        Map<String, String> headers = Collections.singletonMap("SCHILY.realsize", "invalid");
        assertThrows(IOException.class, () -> entry.fillStarSparseData(headers));
    }

    @Test
    public void testProcessPaxHeaderWithInvalidGid() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        assertThrows(IOException.class, () -> entry.processPaxHeader("gid", "invalid"));
    }

    @Test
    public void testProcessPaxHeaderWithInvalidUid() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        assertThrows(IOException.class, () -> entry.processPaxHeader("uid", "invalid"));
    }

    @Test
    public void testProcessPaxHeaderWithInvalidSize() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        assertThrows(IOException.class, () -> entry.processPaxHeader("size", "invalid"));
    }

    @Test
    public void testProcessPaxHeaderWithInvalidDevMinor() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        assertThrows(IOException.class, () -> entry.processPaxHeader("SCHILY.devminor", "invalid"));
    }

    @Test
    public void testProcessPaxHeaderWithInvalidDevMajor() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        assertThrows(IOException.class, () -> entry.processPaxHeader("SCHILY.devmajor", "invalid"));
    }

    @Test
    public void testParsingUtilsParseIntValue() {
        assertEquals(123, ParsingUtils.parseIntValue("123"));
        assertThrows(NumberFormatException.class, () -> ParsingUtils.parseIntValue("invalid"));
    }

    @Test
    public void testParsingUtilsParseLongValue() {
        assertEquals(123L, ParsingUtils.parseLongValue("123"));
        assertThrows(NumberFormatException.class, () -> ParsingUtils.parseLongValue("invalid"));
    }
}