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
    public void testReadNewEntryWithCrc() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("new_crc.cpio")))) {
            CpioArchiveEntry entry = in.getNextCPIOEntry();
            assertNotNull(entry);
            assertEquals(CpioConstants.FORMAT_NEW_CRC, entry.getFormat());
        }
    }

    @Test
    public void testReadNewEntryWithoutCrc() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("new.cpio")))) {
            CpioArchiveEntry entry = in.getNextCPIOEntry();
            assertNotNull(entry);
            assertEquals(CpioConstants.FORMAT_NEW, entry.getFormat());
        }
    }
}