package org.apache.commons.compress.compressors.gzip;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import static org.junit.jupiter.api.Assertions.*;

public class GzipCompressorOutputStreamLLM_Test {

    @Test
    public void testBufferSizeParameter() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GzipParameters params = new GzipParameters();
        params.setBufferSize(1024);
        GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(baos, params);

        assertEquals(1024, gcos.deflateBuffer.length, "Buffer size should be set to the value provided in GzipParameters");
    }

    @Test
    public void testDefaultBufferSize() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GzipParameters params = new GzipParameters();
        GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(baos, params);

        assertEquals(512, gcos.deflateBuffer.length, "Default buffer size should be 512");
    }
}