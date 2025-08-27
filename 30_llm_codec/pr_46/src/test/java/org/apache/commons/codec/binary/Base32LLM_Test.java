package org.apache.commons.codec.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class Base32LLM_Test {

    @Test
    public void testValidateTrailingCharacters() {
        Base32 codec = new Base32(0, null, false, BaseNCodec.PAD_DEFAULT, CodecPolicy.STRICT);
        String validBase32String = "MZXW6YTBOI======";
        try {
            codec.decode(validBase32String);
        } catch (IllegalArgumentException e) {
            fail("Exception should not be thrown for valid Base32 string.");
        }

        String invalidBase32String = "MZXW6YTBOI====="; // Invalid trailing characters
        try {
            codec.decode(invalidBase32String);
            fail("Exception should be thrown for invalid Base32 string.");
        } catch (IllegalArgumentException e) {
            assertEquals("Strict decoding: Last encoded character(s) (before the paddings if any) are valid base 32 alphabet but not a possible encoding. Decoding requires either 2, 4, 5, or 7 trailing 5-bit characters to create bytes.", e.getMessage());
        }
    }
}