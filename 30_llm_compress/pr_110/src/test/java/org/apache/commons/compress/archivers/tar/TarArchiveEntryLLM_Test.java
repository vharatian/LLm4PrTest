package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.junit.Test;

public class TarArchiveEntryLLM_Test implements TarConstants {
    private static final String OS = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
    private static final String ROOT = OS.startsWith("windows") || OS.startsWith("netware") ? "C:\\" : "/";

    @Test
    public void testModTimeInitializationWithCurrentTimeMillis() {
        // Create a new TarArchiveEntry with a name and check the modTime
        final TarArchiveEntry entry = new TarArchiveEntry("testEntry");
        long expectedModTime = System.currentTimeMillis() / TarArchiveEntry.MILLIS_PER_SECOND;
        assertEquals(expectedModTime, entry.getModTime().getTime() / TarArchiveEntry.MILLIS_PER_SECOND);
    }

    @Test
    public void testModTimeInitializationWithCurrentTimeMillisAndPreserveAbsolutePath() {
        // Create a new TarArchiveEntry with a name and preserveAbsolutePath set to true
        final TarArchiveEntry entry = new TarArchiveEntry("testEntry", true);
        long expectedModTime = System.currentTimeMillis() / TarArchiveEntry.MILLIS_PER_SECOND;
        assertEquals(expectedModTime, entry.getModTime().getTime() / TarArchiveEntry.MILLIS_PER_SECOND);
    }

    @Test
    public void testModTimeInitializationWithLinkFlag() {
        // Create a new TarArchiveEntry with a name and linkFlag
        final TarArchiveEntry entry = new TarArchiveEntry("testEntry", LF_NORMAL);
        long expectedModTime = System.currentTimeMillis() / TarArchiveEntry.MILLIS_PER_SECOND;
        assertEquals(expectedModTime, entry.getModTime().getTime() / TarArchiveEntry.MILLIS_PER_SECOND);
    }

    @Test
    public void testModTimeInitializationWithLinkFlagAndPreserveAbsolutePath() {
        // Create a new TarArchiveEntry with a name, linkFlag, and preserveAbsolutePath set to true
        final TarArchiveEntry entry = new TarArchiveEntry("testEntry", LF_NORMAL, true);
        long expectedModTime = System.currentTimeMillis() / TarArchiveEntry.MILLIS_PER_SECOND;
        assertEquals(expectedModTime, entry.getModTime().getTime() / TarArchiveEntry.MILLIS_PER_SECOND);
    }
}