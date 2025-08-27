package org.apache.commons.codec.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class Base64LLM_Test {

    @Test
    public void testValidateCharacterWithInvalidBits() {
        Base64 base64 = new Base64();
        Base64.Context context = base64.new Context();
        context.ibitWorkArea = 0b1000; // Set some bits that should be invalid for 4 bits check

        try {
            base64.decode(new byte[]{'A', 'B', 'C'}, 0, 3, context);
            fail("Expected IllegalArgumentException for invalid bits in context.ibitWorkArea");
        } catch (IllegalArgumentException e) {
            assertEquals("Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible value", e.getMessage());
        }
    }

    @Test
    public void testValidateCharacterWithValidBits() {
        Base64 base64 = new Base64();
        Base64.Context context = base64.new Context();
        context.ibitWorkArea = 0b0000; // Set bits that should be valid for 4 bits check

        try {
            base64.decode(new byte[]{'A', 'B', 'C'}, 0, 3, context);
        } catch (IllegalArgumentException e) {
            fail("Did not expect IllegalArgumentException for valid bits in context.ibitWorkArea");
        }
    }
}