package org.apache.commons.compress.compressors.gzip;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class GzipCompressorInputStreamLLM_Test {

    @Test
    public void testConstructor() throws IOException {
        byte[] gzipData = {31, -117, 8, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ByteArrayInputStream bais = new ByteArrayInputStream(gzipData);
        GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(bais);
        assertNotNull(gzipIn);
    }

    @Test
    public void testReadSingleByte() throws IOException {
        byte[] gzipData = {31, -117, 8, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ByteArrayInputStream bais = new ByteArrayInputStream(gzipData);
        GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(bais);
        int result = gzipIn.read();
        assertEquals(-1, result);
    }

    @Test
    public void testReadByteArray() throws IOException {
        byte[] gzipData = {31, -117, 8, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ByteArrayInputStream bais = new ByteArrayInputStream(gzipData);
        GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(bais);
        byte[] buffer = new byte[10];
        int result = gzipIn.read(buffer);
        assertEquals(-1, result);
    }

    @Test
    public void testClose() throws IOException {
        byte[] gzipData = {31, -117, 8, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ByteArrayInputStream bais = new ByteArrayInputStream(gzipData);
        GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(bais);
        gzipIn.close();
        assertThrows(IOException.class, () -> gzipIn.read());
    }

    @Test
    public void testGetCompressedCount() throws IOException {
        byte[] gzipData = {31, -117, 8, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ByteArrayInputStream bais = new ByteArrayInputStream(gzipData);
        GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(bais);
        assertEquals(0, gzipIn.getCompressedCount());
    }

    @Test
    public void testMatches() {
        byte[] signature = {31, -117};
        assertTrue(GzipCompressorInputStream.matches(signature, signature.length));
    }
}