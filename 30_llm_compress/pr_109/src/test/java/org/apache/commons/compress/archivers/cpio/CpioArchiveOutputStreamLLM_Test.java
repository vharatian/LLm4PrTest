package org.apache.commons.compress.archivers.cpio;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class CpioArchiveOutputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testPadMethod() throws Exception {
        final File output = new File(dir, "testPad.cpio");
        final FileOutputStream out = new FileOutputStream(output);
        try {
            final CpioArchiveOutputStream os = new CpioArchiveOutputStream(out, CpioConstants.FORMAT_NEW);
            os.pad(10); // Test padding with 10 bytes
            os.close();
        } finally {
            out.close();
        }
        // Verify the file size to ensure padding was written
        assertEquals(10, output.length());
    }

    @Test
    public void testWriteBinaryLongMethod() throws Exception {
        final File output = new File(dir, "testWriteBinaryLong.cpio");
        final FileOutputStream out = new FileOutputStream(output);
        try {
            final CpioArchiveOutputStream os = new CpioArchiveOutputStream(out, CpioConstants.FORMAT_NEW);
            os.writeBinaryLong(123456789L, 8, false); // Test writing a binary long
            os.close();
        } finally {
            out.close();
        }
        // Verify the file size to ensure binary long was written
        assertEquals(8, output.length());
    }
}