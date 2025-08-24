package org.apache.commons.compress.archivers.cpio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.FileInputStream;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class CpioArchiveInputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testInitialValues() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("bla.cpio")))) {
            // Check initial values of the modified fields
            assertEquals(false, in.closed);
            assertEquals(0, in.entryBytesRead);
            assertEquals(false, in.entryEOF);
            assertEquals(0, in.crc);
        }
    }

    @Test
    public void testReadAfterClose() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("bla.cpio")))) {
            in.close();
            try {
                in.read();
            } catch (IOException e) {
                assertEquals("Stream closed", e.getMessage());
            }
        }
    }

    @Test
    public void testSkipAfterClose() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("bla.cpio")))) {
            in.close();
            try {
                in.skip(1);
            } catch (IOException e) {
                assertEquals("Stream closed", e.getMessage());
            }
        }
    }
}