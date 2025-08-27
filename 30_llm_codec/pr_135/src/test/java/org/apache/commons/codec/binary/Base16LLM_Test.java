package org.apache.commons.codec.binary;

import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class Base16LLM_Test {
    private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;
    private final Random random = new Random();

    public Random getRandom() {
        return this.random;
    }

    @Test
    public void testDecodeWithOddNumberOfChars() {
        final Base16 base16 = new Base16();
        final String encoded = "A";
        final BaseNCodec.Context context = new BaseNCodec.Context();
        base16.decode(encoded.getBytes(CHARSET_UTF8), 0, 1, context);
        assertEquals(11, context.ibitWorkArea, "ibitWorkArea should be set correctly for odd number of chars");
    }

    @Test
    public void testDecodeWithEvenNumberOfChars() {
        final Base16 base16 = new Base16();
        final String encoded = "A1";
        final BaseNCodec.Context context = new BaseNCodec.Context();
        base16.decode(encoded.getBytes(CHARSET_UTF8), 0, 2, context);
        assertEquals(0, context.ibitWorkArea, "ibitWorkArea should be reset after processing even number of chars");
        assertEquals((byte) 0xA1, context.buffer[0], "Buffer should contain the correct decoded byte");
    }

    @Test
    public void testDecodeWithTrailingCharacter() {
        final Base16 base16 = new Base16();
        final String encoded = "A1B";
        final BaseNCodec.Context context = new BaseNCodec.Context();
        base16.decode(encoded.getBytes(CHARSET_UTF8), 0, 3, context);
        assertEquals(12, context.ibitWorkArea, "ibitWorkArea should be set correctly for trailing character");
    }

    @Test
    public void testDecodeWithMultipleInvocations() {
        final Base16 base16 = new Base16();
        final String encoded = "A1B2C3";
        final BaseNCodec.Context context = new BaseNCodec.Context();
        base16.decode(encoded.getBytes(CHARSET_UTF8), 0, 2, context);
        base16.decode(encoded.getBytes(CHARSET_UTF8), 2, 2, context);
        base16.decode(encoded.getBytes(CHARSET_UTF8), 4, 2, context);
        assertEquals(0, context.ibitWorkArea, "ibitWorkArea should be reset after processing all chars");
        assertEquals((byte) 0xA1, context.buffer[0], "Buffer should contain the correct first decoded byte");
        assertEquals((byte) 0xB2, context.buffer[1], "Buffer should contain the correct second decoded byte");
        assertEquals((byte) 0xC3, context.buffer[2], "Buffer should contain the correct third decoded byte");
    }

    @Test
    public void testDecodeWithSingleCharLeftOver() {
        final Base16 base16 = new Base16();
        final String encoded = "A1B2C";
        final BaseNCodec.Context context = new BaseNCodec.Context();
        base16.decode(encoded.getBytes(CHARSET_UTF8), 0, 5, context);
        assertEquals(13, context.ibitWorkArea, "ibitWorkArea should be set correctly for single char left over");
        assertEquals((byte) 0xA1, context.buffer[0], "Buffer should contain the correct first decoded byte");
        assertEquals((byte) 0xB2, context.buffer[1], "Buffer should contain the correct second decoded byte");
    }
}