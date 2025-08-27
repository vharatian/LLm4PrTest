package org.apache.commons.codec.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class Base64LLM_Test {

    /**
     * Test to ensure that the corrected spelling in the exception message is properly thrown.
     */
    @Test
    public void testValidateTrailingCharacterExceptionMessage() {
        Base64 base64 = new Base64(0, null, false, CodecPolicy.STRICT);
        byte[] invalidInput = new byte[] { 'A' }; // Single character input to trigger the exception

        try {
            base64.decode(invalidInput);
            fail("Expected IllegalArgumentException due to strict decoding with invalid trailing character.");
        } catch (IllegalArgumentException e) {
            assertEquals("Strict decoding: Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible encoding. Decoding requires at least two trailing 6-bit characters to create bytes.", e.getMessage());
        }
    }
}