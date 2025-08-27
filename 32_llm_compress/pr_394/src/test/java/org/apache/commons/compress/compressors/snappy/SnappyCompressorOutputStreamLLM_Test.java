package org.apache.commons.compress.compressors.snappy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.compress.compressors.lz77support.Parameters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SnappyCompressorOutputStreamLLM_Test {

    @Test
    public void testWriteUncompressedSize() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        SnappyCompressorOutputStream scos = new SnappyCompressorOutputStream(baos, 128);

        // Test with a small uncompressed size
        scos.writeUncompressedSize(127);
        byte[] result = baos.toByteArray();
        assertEquals(1, result.length);
        assertEquals(127, result[0]);

        // Reset the stream
        baos.reset();

        // Test with a larger uncompressed size
        scos.writeUncompressedSize(128);
        result = baos.toByteArray();
        assertEquals(2, result.length);
        assertEquals((byte) 0x80, result[0]);
        assertEquals(1, result[1]);
    }

    @Test
    public void testWriteUncompressedSizeWithBoundaryValues() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        SnappyCompressorOutputStream scos = new SnappyCompressorOutputStream(baos, 128);

        // Test with boundary value 0
        scos.writeUncompressedSize(0);
        byte[] result = baos.toByteArray();
        assertEquals(1, result.length);
        assertEquals(0, result[0]);

        // Reset the stream
        baos.reset();

        // Test with boundary value Long.MAX_VALUE
        scos.writeUncompressedSize(Long.MAX_VALUE);
        result = baos.toByteArray();
        assertTrue(result.length > 1);
        assertEquals((byte) 0xFF, result[0] & 0xFF); // Check the first byte is 0xFF
    }
}