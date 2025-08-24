package org.apache.commons.compress.archivers.cpio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.InputStream;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

public class CpioArchiveInputStreamLLM_Test extends AbstractTestCase {
    @Test
    public void multiByteReadConsistentlyReturnsMinusOneAtEof() throws Exception {
        final byte[] buf = new byte[2];
        try (InputStream in = newInputStream("bla.cpio"); CpioArchiveInputStream archive = new CpioArchiveInputStream(in)) {
            final ArchiveEntry e = archive.getNextEntry();
            IOUtils.toByteArray(archive);
            assertEquals(-1, archive.read(buf));
            assertEquals(-1, archive.read(buf));
        }
    }

    @Test
    public void singleByteReadConsistentlyReturnsMinusOneAtEof() throws Exception {
        try (InputStream in = newInputStream("bla.cpio"); CpioArchiveInputStream archive = new CpioArchiveInputStream(in)) {
            final ArchiveEntry e = archive.getNextEntry();
            IOUtils.toByteArray(archive);
            assertEquals(-1, archive.read());
            assertEquals(-1, archive.read());
        }
    }

    @Test
    public void testCpioUnarchive() throws Exception {
        final StringBuilder expected = new StringBuilder();
        expected.append("./test1.xml<?xml version=\"1.0\"?>\n");
        expected.append("<empty/>./test2.xml<?xml version=\"1.0\"?>\n");
        expected.append("<empty/>\n");
        final StringBuilder result = new StringBuilder();
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(newInputStream("bla.cpio"))) {
            CpioArchiveEntry entry;
            while ((entry = (CpioArchiveEntry) in.getNextEntry()) != null) {
                result.append(entry.getName());
                int tmp;
                while ((tmp = in.read()) != -1) {
                    result.append((char) tmp);
                }
            }
        }
        assertEquals(result.toString(), expected.toString());
    }

    @Test
    public void testCpioUnarchiveCreatedByRedlineRpm() throws Exception {
        int count = 0;
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(newInputStream("redline.cpio"))) {
            CpioArchiveEntry entry = null;
            while ((entry = (CpioArchiveEntry) in.getNextEntry()) != null) {
                count++;
                assertNotNull(entry);
            }
        }
        assertEquals(count, 1);
    }

    @Test
    public void testCpioUnarchiveMultibyteCharName() throws Exception {
        int count = 0;
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(newInputStream("COMPRESS-459.cpio"), "UTF-8")) {
            CpioArchiveEntry entry = null;
            while ((entry = (CpioArchiveEntry) in.getNextEntry()) != null) {
                count++;
                assertNotNull(entry);
            }
        }
        assertEquals(2, count);
    }
}