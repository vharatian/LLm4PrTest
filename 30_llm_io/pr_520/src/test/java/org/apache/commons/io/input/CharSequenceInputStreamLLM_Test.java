package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.CharsetsTest;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class CharSequenceInputStreamLLM_Test {

    private static final String UTF_8 = StandardCharsets.UTF_8.name();
    private static final String TEST_STRING = "\u00e0 peine arriv\u00e9s nous entr\u00e2mes dans sa chambre";

    /**
     * Test to ensure the default charset is used when null is passed.
     */
    @Test
    public void testDefaultCharsetWhenNull() throws IOException {
        try (CharSequenceInputStream in = new CharSequenceInputStream("A", (Charset) null)) {
            IOUtils.toByteArray(in);
            assertEquals(Charset.defaultCharset(), in.getCharsetEncoder().charset());
        }
        try (CharSequenceInputStream in = CharSequenceInputStream.builder().setCharSequence("test").setCharset((Charset) null).get()) {
            IOUtils.toByteArray(in);
            assertEquals(Charset.defaultCharset(), in.getCharsetEncoder().charset());
        }
    }

    /**
     * Test to ensure the default charset is used when null is passed as a string.
     */
    @Test
    public void testDefaultCharsetWhenNullString() throws IOException {
        try (CharSequenceInputStream in = new CharSequenceInputStream("A", (String) null)) {
            IOUtils.toByteArray(in);
            assertEquals(Charset.defaultCharset(), in.getCharsetEncoder().charset());
        }
        try (CharSequenceInputStream in = CharSequenceInputStream.builder().setCharSequence("test").setCharset((String) null).get()) {
            IOUtils.toByteArray(in);
            assertEquals(Charset.defaultCharset(), in.getCharsetEncoder().charset());
        }
    }

    /**
     * Test to ensure that reset() calls mark(0) as per the new change.
     */
    @Test
    public void testResetCallsMark() throws IOException {
        try (CharSequenceInputStream in = new CharSequenceInputStream("1234", UTF_8)) {
            in.mark(0);
            assertEquals('1', in.read());
            in.reset();
            assertEquals('1', in.read());
            assertEquals('2', in.read());
            in.reset();
            assertEquals('1', in.read());
        }
    }

    /**
     * Test to ensure that reset() works correctly after multiple calls.
     */
    @Test
    public void testMultipleResets() throws IOException {
        try (CharSequenceInputStream in = new CharSequenceInputStream("1234", UTF_8)) {
            in.mark(0);
            assertEquals('1', in.read());
            in.reset();
            assertEquals('1', in.read());
            in.reset();
            assertEquals('1', in.read());
        }
    }

    /**
     * Test to ensure mark and reset work correctly with different charsets.
     */
    @ParameterizedTest
    @MethodSource(CharsetsTest.REQUIRED_CHARSETS)
    public void testMarkResetWithCharsets(final String csName) throws Exception {
        try (InputStream r = new CharSequenceInputStream("test", csName)) {
            assertEquals(2, r.skip(2));
            r.mark(0);
            assertEquals('s', r.read(), csName);
            assertEquals('t', r.read(), csName);
            assertEquals(-1, r.read(), csName);
            r.reset();
            assertEquals('s', r.read(), csName);
            assertEquals('t', r.read(), csName);
            assertEquals(-1, r.read(), csName);
            r.reset();
            r.reset();
        }
    }
}