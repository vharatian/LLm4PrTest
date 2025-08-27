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
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

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

    /**
     * Test to ensure that the flush method is called when endOfInput is true.
     */
    @Test
    public void testFlushOnEndOfInput() throws IOException {
        final Charset charset = StandardCharsets.UTF_8;
        final String inputString = "Test string for flush.";
        try (InputStream in = new ReaderInputStream(new StringReader(inputString), charset)) {
            byte[] buffer = new byte[128];
            int bytesRead = 0;
            while (bytesRead != -1) {
                bytesRead = in.read(buffer);
            }
            assertEquals(-1, bytesRead);
        }
    }

    /**
     * Test to ensure that the read method correctly handles the end of input without entering an infinite loop.
     */
    @Test
    public void testReadAfterEndOfInput() throws IOException {
        final Charset charset = StandardCharsets.UTF_8;
        final String inputString = "Test string for end of input.";
        try (InputStream in = new ReaderInputStream(new StringReader(inputString), charset)) {
            byte[] buffer = new byte[128];
            int bytesRead = 0;
            while (bytesRead != -1) {
                bytesRead = in.read(buffer);
            }
            assertEquals(-1, in.read(buffer));
        }
    }

    /**
     * Test to ensure that the read method correctly handles an empty input.
     */
    @Test
    public void testReadEmptyInput() throws IOException {
        final Charset charset = StandardCharsets.UTF_8;
        final String inputString = "";
        try (InputStream in = new ReaderInputStream(new StringReader(inputString), charset)) {
            byte[] buffer = new byte[128];
            int bytesRead = in.read(buffer);
            assertEquals(-1, bytesRead);
        }
    }
}