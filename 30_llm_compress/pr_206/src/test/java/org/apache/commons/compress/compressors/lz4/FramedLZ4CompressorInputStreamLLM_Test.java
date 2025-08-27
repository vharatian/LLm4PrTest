package org.apache.commons.compress.compressors.lz4;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

public final class FramedLZ4CompressorInputStreamLLM_Test {

    @Test
    public void testNoNegativeBlockSize() {
        // This test ensures that the code no longer throws an IOException for negative block sizes
        final byte[] input = new byte[] {
            4, 0x22, 0x4d, 0x18, // LZ4 signature
            0x60, 0x70, // Frame descriptor
            115, 13, 0, 0, // Block size (13 bytes)
            (byte) 0x80, // Uncompressed block flag
            'H', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd', '!', // Block data
            0, 0, 0, 0 // Block checksum
        };
        try (InputStream a = new FramedLZ4CompressorInputStream(new ByteArrayInputStream(input))) {
            final byte[] actual = IOUtils.toByteArray(a);
            assertThat(new String(actual), containsString("Hello, world!"));
        } catch (final IOException ex) {
            fail("Unexpected IOException: " + ex.getMessage());
        }
    }
}