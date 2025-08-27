package org.apache.commons.compress.archivers.tar;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.apache.commons.compress.AbstractTestCase.getPath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.CharsetNames;
import org.junit.Test;

public class TarArchiveEntryLLM_Test implements TarConstants {
    private static final String OS = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
    private static final String ROOT = OS.startsWith("windows") || OS.startsWith("netware") ? "C:\\" : "/";

    @Test
    public void testDefaultValues() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        assertEquals(0, entry.getLongUserId());
        assertEquals(0, entry.getLongGroupId());
        assertEquals(0, entry.getSize());
        assertEquals(0, entry.getDevMajor());
        assertEquals(0, entry.getDevMinor());
        assertEquals(false, entry.isPaxGNU1XSparse());
    }

    @Test
    public void testSetAndGetUserId() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        entry.setUserId(1000L);
        assertEquals(1000L, entry.getLongUserId());
    }

    @Test
    public void testSetAndGetGroupId() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        entry.setGroupId(1000L);
        assertEquals(1000L, entry.getLongGroupId());
    }

    @Test
    public void testSetAndGetSize() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        entry.setSize(1024L);
        assertEquals(1024L, entry.getSize());
    }

    @Test
    public void testSetAndGetDevMajor() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        entry.setDevMajor(1);
        assertEquals(1, entry.getDevMajor());
    }

    @Test
    public void testSetAndGetDevMinor() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        entry.setDevMinor(1);
        assertEquals(1, entry.getDevMinor());
    }

    @Test
    public void testSetAndGetPaxGNU1XSparse() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        entry.setPaxGNU1XSparse(true);
        assertTrue(entry.isPaxGNU1XSparse());
    }
}