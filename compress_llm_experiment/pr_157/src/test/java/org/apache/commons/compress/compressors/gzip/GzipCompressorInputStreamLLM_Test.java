package org.apache.commons.compress.compressors.gzip;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GzipCompressorInputStreamLLM_Test {

    @Test
    public void testReadWithFinalIntRet() throws IOException {
        byte[] input = "Test data for GzipCompressorInputStream".getBytes();
        byte[] compressedData = compressData(input);

        try (GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(new ByteArrayInputStream(compressedData))) {
            byte[] result = new byte[input.length];
            int bytesRead = gzipIn.read(result);
            assertEquals(input.length, bytesRead);
            assertArrayEquals(input, result);
        }
    }

    private byte[] compressData(byte[] data) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(data);
            gzipOutputStream.finish();
            return byteArrayOutputStream.toByteArray();
        }
    }
}