package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ReaderInputStreamLLM_Test {

    private static final String TEST_STRING = "\u00e0 peine arriv\u00e9s nous entr\u00e2mes dans sa chambre";
    private static final String LARGE_TEST_STRING;

    static {
        final StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            buffer.append(TEST_STRING);
        }
        LARGE_TEST_STRING = buffer.toString();
    }

    private final Random random = new Random();

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    public void testAvailableAlwaysReturnsZero() throws IOException {
        try (InputStream in = new ReaderInputStream(new StringReader(TEST_STRING), StandardCharsets.UTF_8)) {
            assertEquals(0, in.available());
        }
    }

    @Test
    public void testMarkNotSupported() throws IOException {
        try (InputStream in = new ReaderInputStream(new StringReader(TEST_STRING), StandardCharsets.UTF_8)) {
            assertThrows(IOException.class, () -> in.mark(0));
        }
    }

    @Test
    public void testResetNotSupported() throws IOException {
        try (InputStream in = new ReaderInputStream(new StringReader(TEST_STRING), StandardCharsets.UTF_8)) {
            assertThrows(IOException.class, in::reset);
        }
    }

    @Test
    public void testCharsetEncoderResetNotCalled() throws IOException {
        CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
        encoder.encode(CharBuffer.wrap("test")); // Use the encoder before passing it to ReaderInputStream
        try (InputStream in = new ReaderInputStream(new StringReader(TEST_STRING), encoder)) {
            byte[] actual = IOUtils.toByteArray(in);
            byte[] expected = TEST_STRING.getBytes(StandardCharsets.UTF_8);
            assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
    }

    @Test
    public void testCharsetEncoderResetNotCalledWithBufferSize() throws IOException {
        CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
        encoder.encode(CharBuffer.wrap("test")); // Use the encoder before passing it to ReaderInputStream
        try (InputStream in = new ReaderInputStream(new StringReader(TEST_STRING), encoder, 1024)) {
            byte[] actual = IOUtils.toByteArray(in);
            byte[] expected = TEST_STRING.getBytes(StandardCharsets.UTF_8);
            assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
    }
}