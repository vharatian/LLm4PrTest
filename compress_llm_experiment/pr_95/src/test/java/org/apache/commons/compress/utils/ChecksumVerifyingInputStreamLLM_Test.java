package org.apache.commons.compress.utils;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import static org.junit.Assert.assertEquals;

public class ChecksumVerifyingInputStreamLLM_Test {

    @Test
    public void testGetBytesRemaining() throws IOException {
        CRC32 crc32 = new CRC32();
        byte[] byteArray = new byte[10];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        ChecksumVerifyingInputStream checksumVerifyingInputStream = new ChecksumVerifyingInputStream(crc32, byteArrayInputStream, 10L, 0L);

        // Initially, bytesRemaining should be equal to the size provided in the constructor
        assertEquals(10L, checksumVerifyingInputStream.getBytesRemaining());

        // Read one byte and check bytesRemaining
        checksumVerifyingInputStream.read();
        assertEquals(9L, checksumVerifyingInputStream.getBytesRemaining());

        // Read remaining bytes and check bytesRemaining
        byte[] buffer = new byte[9];
        checksumVerifyingInputStream.read(buffer);
        assertEquals(0L, checksumVerifyingInputStream.getBytesRemaining());
    }
}