package org.apache.commons.codec.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class Base64LLM_Test {

    /**
     * Test the validateCharacter method to ensure it correctly identifies invalid trailing characters.
     */
    @Test
    public void testValidateCharacter() {
        Base64.Context context = new Base64.Context();

        // Test with MASK_4BITS
        context.ibitWorkArea = 0b00001111; // Last 4 bits are not zero
        try {
            Base64.validateCharacter(0xF, context);
            fail("Expected IllegalArgumentException for non-zero trailing bits with MASK_4BITS");
        } catch (IllegalArgumentException e) {
            assertEquals("Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible value. Expected the discarded bits to be zero.", e.getMessage());
        }

        context.ibitWorkArea = 0b00000000; // Last 4 bits are zero
        Base64.validateCharacter(0xF, context); // Should not throw exception

        // Test with MASK_2BITS
        context.ibitWorkArea = 0b00000011; // Last 2 bits are not zero
        try {
            Base64.validateCharacter(0x3, context);
            fail("Expected IllegalArgumentException for non-zero trailing bits with MASK_2BITS");
        } catch (IllegalArgumentException e) {
            assertEquals("Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible value. Expected the discarded bits to be zero.", e.getMessage());
        }

        context.ibitWorkArea = 0b00000000; // Last 2 bits are zero
        Base64.validateCharacter(0x3, context); // Should not throw exception
    }

    /**
     * Test the decode method to ensure it correctly handles cases with trailing bits.
     */
    @Test
    public void testDecodeWithTrailingBits() {
        Base64 codec = new Base64();

        // Test with 12 trailing bits
        byte[] encoded12Bits = "QUJDRA==".getBytes(); // Encodes "ABCD" with 12 trailing bits
        byte[] decoded12Bits = codec.decode(encoded12Bits);
        assertEquals("ABCD", new String(decoded12Bits));

        // Test with 18 trailing bits
        byte[] encoded18Bits = "QUJDREVGRw==".getBytes(); // Encodes "ABCDEFG" with 18 trailing bits
        byte[] decoded18Bits = codec.decode(encoded18Bits);
        assertEquals("ABCDEFG", new String(decoded18Bits));
    }
}