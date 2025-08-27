package org.apache.commons.compress.archivers.sevenz;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.MemoryLimitException;
import org.junit.jupiter.api.Test;
import org.tukaani.xz.LZMAInputStream;

public class LZMADecoderLLM_Test {

    @Test
    public void testDecodeWithRelaxedEndCondition() throws IOException {
        LZMADecoder decoder = new LZMADecoder();
        byte[] properties = new byte[] { 0x5D, 0x00, 0x00, 0x10, 0x00 };
        Coder coder = new Coder();
        coder.properties = properties;
        byte[] data = new byte[100];
        InputStream in = new ByteArrayInputStream(data);
        long uncompressedLength = 100L;
        int maxMemoryLimitInKb = 1024;

        LZMAInputStream lzmaIn = (LZMAInputStream) decoder.decode("testArchive", in, uncompressedLength, coder, null, maxMemoryLimitInKb);
        assertTrue(lzmaIn.isRelaxedEndConditionEnabled(), "Relaxed end condition should be enabled");
    }

    @Test
    public void testDecodeMemoryLimitExceeded() {
        LZMADecoder decoder = new LZMADecoder();
        byte[] properties = new byte[] { 0x5D, 0x00, 0x00, 0x10, 0x00 };
        Coder coder = new Coder();
        coder.properties = properties;
        byte[] data = new byte[100];
        InputStream in = new ByteArrayInputStream(data);
        long uncompressedLength = 100L;
        int maxMemoryLimitInKb = 1; // Set a very low memory limit to trigger the exception

        assertThrows(MemoryLimitException.class, () -> {
            decoder.decode("testArchive", in, uncompressedLength, coder, null, maxMemoryLimitInKb);
        });
    }
}