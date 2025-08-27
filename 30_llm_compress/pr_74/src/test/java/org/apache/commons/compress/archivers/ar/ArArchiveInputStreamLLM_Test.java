package org.apache.commons.compress.archivers.ar;

import static org.junit.Assert.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class ArArchiveInputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testGetNextArEntryReturnsNullOnZeroRead() throws Exception {
        try (final FileInputStream fis = new FileInputStream(getFile("empty.ar"));
             final ArArchiveInputStream s = new ArArchiveInputStream(new BufferedInputStream(fis))) {
            assertNull(s.getNextEntry());
        }
    }

    @Test
    public void testGetNextArEntryThrowsIOExceptionOnTruncatedArchive() throws Exception {
        try (final FileInputStream fis = new FileInputStream(getFile("truncated.ar"));
             final ArArchiveInputStream s = new ArArchiveInputStream(new BufferedInputStream(fis))) {
            try {
                s.getNextEntry();
                fail("Expected IOException due to truncated archive");
            } catch (IOException e) {
                assertEquals("truncated ar archive", e.getMessage());
            }
        }
    }
}