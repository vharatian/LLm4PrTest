package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class CharSequenceInputStreamLLM_Test {

    private static final String UTF_8 = StandardCharsets.UTF_8.name();
    private static final String INVALID_CHAR_SEQUENCE = "\uD800"; // Invalid surrogate pair

    @Test
    public void testConstructorInitialFillBuffer() {
        // Test that the constructor fills the buffer initially
        String input = "test";
        try (InputStream in = new CharSequenceInputStream(input, UTF_8)) {
            assertTrue(in.available() > 0, "Buffer should be initially filled");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConstructorHandlesCharacterCodingException() {
        // Test that the constructor handles CharacterCodingException properly
        try (InputStream in = new CharSequenceInputStream(INVALID_CHAR_SEQUENCE, UTF_8)) {
            assertThrows(CharacterCodingException.class, () -> {
                while (in.read() != -1) {
                    // Read until exception is thrown
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAvailableAfterConstructor() {
        // Test that available() returns the correct value after constructor
        String input = "test";
        try (InputStream in = new CharSequenceInputStream(input, UTF_8)) {
            int expectedAvailable = input.getBytes(UTF_8).length;
            assertEquals(expectedAvailable, in.available(), "Available bytes should match input length");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}