package org.apache.commons.compress.compressors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class CompressorStreamProviderLLM_Test {

    @Test
    public void testCreateCompressorOutputStream() {
        CompressorStreamProvider provider = new CompressorStreamProviderImpl();
        OutputStream out = new ByteArrayOutputStream();
        try {
            CompressorOutputStream cos = provider.createCompressorOutputStream("gzip", out);
            assertNotNull(cos, "CompressorOutputStream should not be null");
        } catch (CompressorException e) {
            fail("CompressorException should not be thrown");
        }
    }
}