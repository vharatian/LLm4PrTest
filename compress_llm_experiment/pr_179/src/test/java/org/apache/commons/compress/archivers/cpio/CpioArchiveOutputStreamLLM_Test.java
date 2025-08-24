package org.apache.commons.compress.archivers.cpio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class CpioArchiveOutputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testWriteOldBinary() throws Exception {
        final File f = getFile("test1.xml");
        final File output = new File(dir, "test.cpio");
        final FileOutputStream out = new FileOutputStream(output);
        InputStream in = null;
        try {
            final CpioArchiveOutputStream os =
                new CpioArchiveOutputStream(out, CpioConstants.FORMAT_OLD_BINARY);
            os.putArchiveEntry(new CpioArchiveEntry(CpioConstants.FORMAT_OLD_BINARY, f, "test1.xml"));
            IOUtils.copy(in = new FileInputStream(f), os);
            in.close();
            in = null;
            os.closeArchiveEntry();
            os.close();
        } finally {
            if (in != null) {
                in.close();
            }
            out.close();
        }
        try {
            in = new CpioArchiveInputStream(new FileInputStream(output));
            final CpioArchiveEntry e = ((CpioArchiveInputStream) in).getNextCPIOEntry();
            assertEquals("test1.xml", e.getName());
            assertNull(((CpioArchiveInputStream) in).getNextEntry());
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    // Test to ensure the default values of 'closed' and 'crc' are correctly initialized
    @Test
    public void testDefaultValues() throws Exception {
        final FileOutputStream out = new FileOutputStream(new File(dir, "test.cpio"));
        try {
            final CpioArchiveOutputStream os = new CpioArchiveOutputStream(out, CpioConstants.FORMAT_NEW);
            // Check default value of 'closed'
            assertEquals(false, os.closed);
            // Check default value of 'crc'
            assertEquals(0, os.crc);
            os.close();
        } finally {
            out.close();
        }
    }
}