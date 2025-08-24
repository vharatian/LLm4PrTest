package org.apache.commons.compress.archivers.tar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.util.Locale;
import org.junit.jupiter.api.Test;

public class TarArchiveEntryLLM_Test {

    private static final String OS = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
    private static final String ROOT = OS.startsWith("windows") || OS.startsWith("netware") ? "C:\\" : "/";

    @Test
    public void testNormalizeFileNameWindows() {
        assumeTrue(OS.startsWith("windows"));
        String fileName = "C:\\some\\path\\file.txt";
        String normalized = TarArchiveEntry.normalizeFileName(fileName, false);
        assertEquals("some/path/file.txt", normalized);
    }

    @Test
    public void testNormalizeFileNameNetware() {
        assumeTrue(OS.contains("netware"));
        String fileName = "SYS:some/path/file.txt";
        String normalized = TarArchiveEntry.normalizeFileName(fileName, false);
        assertEquals("some/path/file.txt", normalized);
    }

    @Test
    public void testNormalizeFileNameUnix() {
        assumeTrue(!OS.startsWith("windows") && !OS.contains("netware"));
        String fileName = "/some/path/file.txt";
        String normalized = TarArchiveEntry.normalizeFileName(fileName, false);
        assertEquals("some/path/file.txt", normalized);
    }

    @Test
    public void testNormalizeFileNamePreserveAbsolutePath() {
        String fileName = "/some/path/file.txt";
        String normalized = TarArchiveEntry.normalizeFileName(fileName, true);
        assertEquals("/some/path/file.txt", normalized);
    }

    @Test
    public void testNormalizeFileNameWindowsPreserveAbsolutePath() {
        assumeTrue(OS.startsWith("windows"));
        String fileName = "C:\\some\\path\\file.txt";
        String normalized = TarArchiveEntry.normalizeFileName(fileName, true);
        assertEquals("C:/some/path/file.txt", normalized);
    }

    @Test
    public void testNormalizeFileNameNetwarePreserveAbsolutePath() {
        assumeTrue(OS.contains("netware"));
        String fileName = "SYS:some/path/file.txt";
        String normalized = TarArchiveEntry.normalizeFileName(fileName, true);
        assertEquals("SYS:some/path/file.txt", normalized);
    }
}