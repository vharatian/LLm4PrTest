package org.apache.commons.codec.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class PercentCodecLLM_Test {

    @Test
    public void testStaticEscapeChar() throws Exception {
        // Test to ensure ESCAPE_CHAR is static and retains its value
        PercentCodec percentCodec = new PercentCodec();
        final String input = "test%test";
        byte[] encoded = percentCodec.encode(input.getBytes(StandardCharsets.UTF_8));
        final String encodedS = new String(encoded, "UTF-8");
        byte[] decoded = percentCodec.decode(encoded);
        final String decodedS = new String(decoded, "UTF-8");
        assertEquals("Static ESCAPE_CHAR encoding test", "test%25test", encodedS);
        assertEquals("Static ESCAPE_CHAR decoding test", input, decodedS);
    }
}