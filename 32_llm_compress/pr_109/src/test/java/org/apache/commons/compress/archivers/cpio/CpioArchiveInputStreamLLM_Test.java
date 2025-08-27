package org.apache.commons.compress.archivers.cpio;

import static org.junit.Assert.*;
import java.io.FileInputStream;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class CpioArchiveInputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testTmpbufInitialization() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("bla.cpio")))) {
            assertNotNull(in.tmpbuf);
            assertEquals(4096, in.tmpbuf.length);
        }
    }

    @Test
    public void testReadBinaryLong() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("bla.cpio")))) {
            byte[] buffer = new byte[2];
            in.readFully(buffer, 0, buffer.length);
            long result = in.readBinaryLong(2, false);
            assertTrue(result >= 0);
        }
    }

    @Test
    public void testReadAsciiLong() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("bla.cpio")))) {
            byte[] buffer = new byte[6];
            in.readFully(buffer, 0, buffer.length);
            long result = in.readAsciiLong(6, 8);
            assertTrue(result >= 0);
        }
    }

    @Test
    public void testReadCString() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("bla.cpio")))) {
            byte[] buffer = new byte[6];
            in.readFully(buffer, 0, buffer.length);
            String result = in.readCString(6);
            assertNotNull(result);
            assertFalse(result.isEmpty());
        }
    }
}