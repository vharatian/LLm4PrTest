package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class CharSequenceInputStreamLLM_Test {

    @Test
    public void testCharacterCodingExceptionHandling() throws IOException {
        final Charset charset = StandardCharsets.UTF_8;
        final String invalidInput = "\uD800"; // Invalid surrogate pair

        CharSequenceInputStream inputStream = new CharSequenceInputStream(invalidInput, charset, 512);

        // Ensure that the buffer is cleared and flipped correctly when CharacterCodingException is thrown
        assertThrows(CharacterCodingException.class, () -> {
            inputStream.fillBuffer();
        });

        // Check that the buffer is flipped after being cleared
        assertEquals(0, inputStream.available());
    }
}