package org.apache.commons.compress.archivers.sevenz;

import org.apache.commons.compress.MemoryLimitException;
import org.junit.jupiter.api.Test;
import org.tukaani.xz.LZMA2Options;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LZMA2DecoderLLM_Test {

    @Test
    void testDecodeWithMemoryLimitExceeded() {
        LZMA2Decoder decoder = new LZMA2Decoder();
        InputStream in = new ByteArrayInputStream(new byte[]{});
        Coder coder = new Coder();
        coder.properties = new byte[]{0x20}; // Example property value
        int maxMemoryLimitInKb = 1; // Set a very low memory limit to trigger the exception

        assertThrows(MemoryLimitException.class, () -> {
            decoder.decode("archive.7z", in, 100L, coder, null, maxMemoryLimitInKb);
        });
    }

    @Test
    void testDecodeWithinMemoryLimit() throws IOException {
        LZMA2Decoder decoder = new LZMA2Decoder();
        InputStream in = new ByteArrayInputStream(new byte[]{});
        Coder coder = new Coder();
        coder.properties = new byte[]{0x20}; // Example property value
        int maxMemoryLimitInKb = 1024 * 1024; // Set a high memory limit to avoid the exception

        InputStream result = decoder.decode("archive.7z", in, 100L, coder, null, maxMemoryLimitInKb);
        assertNotNull(result);
    }
}