package org.apache.commons.codec.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class Base64LLM_Test {

    @Test
    public void testValidateTrailingCharacter() {
        Base64 base64 = new Base64();
        try {
            base64.decode("A".getBytes());
            fail("Expected IllegalArgumentException for single trailing character");
        } catch (IllegalArgumentException e) {
            assertEquals("Strict decoding: Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible encoding. Decoding requries at least two trailing 6-bit characters to create bytes.", e.getMessage());
        }
    }

    @Test
    public void testValidateCharacterWithStrictDecoding() {
        Base64 base64 = new Base64();
        try {
            base64.decode("AA==".getBytes());
            fail("Expected IllegalArgumentException for invalid trailing bits");
        } catch (IllegalArgumentException e) {
            assertEquals("Strict decoding: Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible encoding. Expected the discarded bits from the character to be zero.", e.getMessage());
        }
    }
}