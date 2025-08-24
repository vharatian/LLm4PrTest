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
    public void testWriteAsciiLong() throws Exception {
        final File output = new File(dir, "test.cpio");
        final FileOutputStream out = new FileOutputStream(output);
        try {
            final CpioArchiveOutputStream os = new CpioArchiveOutputStream(out, CpioConstants.FORMAT_NEW);
            os.writeAsciiLong(123456789L, 8, 16);
            os.close();
        } finally {
            out.close();
        }
        // Read the file back and verify the content
        try (InputStream in = new FileInputStream(output)) {
            byte[] buffer = new byte[8];
            int bytesRead = in.read(buffer);
            assertEquals(8, bytesRead);
            String result = new String(buffer, "US-ASCII");
            assertEquals("075bcd15", result);
        }
    }
}