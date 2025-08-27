package org.apache.commons.compress.archivers.zip;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_16BE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.ByteBuffer;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class NioZipEncodingLLM_Test {

    private static final String UMLAUTS = "\u00e4\u00f6\u00fc";
    private static final String RAINBOW_EMOJI = "\ud83c\udf08";

    @Test
    public void testOversizedBufferHandling() {
        // Test to ensure the handling of oversized buffer is correct
        final NioZipEncoding e = new NioZipEncoding(US_ASCII, false);
        final ByteBuffer bb = e.encode(UMLAUTS + RAINBOW_EMOJI);
        final int off = bb.arrayOffset();
        final byte[] result = Arrays.copyOfRange(bb.array(), off, off + bb.limit() - bb.position());
        assertEquals("%U00E4%U00F6%U00FC%UD83C%UDF08", new String(result, US_ASCII));
    }

    @Test
    public void testExactBufferSizeHandling() {
        // Test to ensure the handling of exact buffer size is correct
        final NioZipEncoding e = new NioZipEncoding(US_ASCII, false);
        final ByteBuffer bb = e.encode(UMLAUTS);
        final int off = bb.arrayOffset();
        final byte[] result = Arrays.copyOfRange(bb.array(), off, off + bb.limit() - bb.position());
        assertEquals("%U00E4%U00F6%U00FC", new String(result, US_ASCII));
    }
}