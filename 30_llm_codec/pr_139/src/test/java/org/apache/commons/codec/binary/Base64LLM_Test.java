package org.apache.commons.codec.binary;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base64LLM_Test {

    @Test
    public void testIsBase64WithCorrectDocumentation() {
        // Test for the corrected documentation in isBase64(byte octet)
        assertTrue(Base64.isBase64((byte) 'A'), "Expected 'A' to be recognized as Base64");
        assertFalse(Base64.isBase64((byte) '$'), "Expected '$' not to be recognized as Base64");
    }

    @Test
    public void testConstructorDocumentation() {
        // Test for the corrected documentation in constructors
        Base64 base64 = new Base64(64);
        assertNotNull(base64, "Base64 instance should be created with line length 64");

        base64 = new Base64(64, new byte[] {'\n'});
        assertNotNull(base64, "Base64 instance should be created with line length 64 and line separator '\\n'");

        base64 = new Base64(64, new byte[] {'\n'}, true);
        assertNotNull(base64, "Base64 instance should be created with line length 64, line separator '\\n', and URL safe mode");

        base64 = new Base64(64, new byte[] {'\n'}, true, CodecPolicy.STRICT);
        assertNotNull(base64, "Base64 instance should be created with line length 64, line separator '\\n', URL safe mode, and strict decoding policy");
    }

    @Test
    public void testIsInAlphabetWithCorrectDocumentation() {
        // Test for the corrected documentation in isInAlphabet(byte octet)
        Base64 base64 = new Base64();
        assertTrue(base64.isInAlphabet((byte) 'A'), "Expected 'A' to be recognized as Base64 alphabet");
        assertFalse(base64.isInAlphabet((byte) '$'), "Expected '$' not to be recognized as Base64 alphabet");
    }
}