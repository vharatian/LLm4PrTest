package org.apache.commons.compress.archivers.ar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class ArArchiveInputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testInitialValues() throws IOException {
        try (FileInputStream fis = new FileInputStream(getFile("bla.ar"));
             ArArchiveInputStream s = new ArArchiveInputStream(new BufferedInputStream(fis))) {
            // Ensure initial values are set correctly
            assertEquals(0, s.getBytesRead());
            assertNull(s.getCurrentEntry());
        }
    }

    @Test
    public void testOffsetIncrementsCorrectly() throws IOException {
        try (FileInputStream fis = new FileInputStream(getFile("bla.ar"));
             ArArchiveInputStream s = new ArArchiveInputStream(new BufferedInputStream(fis))) {
            ArchiveEntry entry = s.getNextEntry();
            long initialOffset = s.getBytesRead();
            byte[] buffer = new byte[10];
            s.read(buffer);
            long newOffset = s.getBytesRead();
            assertEquals(10, newOffset - initialOffset);
        }
    }

    @Test
    public void testNameBufferInitialization() throws IOException {
        try (FileInputStream fis = new FileInputStream(getFile("longfile_gnu.ar"));
             ArArchiveInputStream s = new ArArchiveInputStream(new BufferedInputStream(fis))) {
            ArchiveEntry entry = s.getNextEntry();
            assertThat(s.getNameBuffer(), not(nullValue()));
        }
    }

    @Test
    public void testCurrentEntryInitialization() throws IOException {
        try (FileInputStream fis = new FileInputStream(getFile("bla.ar"));
             ArArchiveInputStream s = new ArArchiveInputStream(new BufferedInputStream(fis))) {
            ArchiveEntry entry = s.getNextEntry();
            assertThat(s.getCurrentEntry(), not(nullValue()));
        }
    }
}