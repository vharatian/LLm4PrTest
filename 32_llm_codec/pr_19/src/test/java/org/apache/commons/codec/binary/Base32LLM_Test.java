package org.apache.commons.codec.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

public class Base32LLM_Test {

    @Test
    public void testDecodeWithInvalidCharacter() {
        Base32 codec = new Base32();
        byte[] input = "MZXW6YTB$".getBytes(); // $ is an invalid character
        assertThrows(IllegalArgumentException.class, () -> {
            codec.decode(input);
        });
    }

    @Test
    public void testDecodeWithValidCharacterButInvalidValue() {
        Base32 codec = new Base32();
        byte[] input = "MZXW6YTB".getBytes(); // Valid characters but invalid base32 value
        assertThrows(IllegalArgumentException.class, () -> {
            codec.decode(input);
        });
    }

    @Test
    public void testDecodeWithValidCharacterAndValidValue() {
        Base32 codec = new Base32();
        byte[] input = "MZXW6YTB".getBytes(); // Valid base32 encoded value
        byte[] expectedOutput = "fooba".getBytes();
        assertArrayEquals(expectedOutput, codec.decode(input));
    }

    @Test
    public void testValidateCharacter() {
        Base32 codec = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        context.lbitWorkArea = 0b00000010; // Set the last 2 bits to 10
        assertThrows(IllegalArgumentException.class, () -> {
            codec.decode(new byte[]{'M', 'Z', 'X', 'W', '6', 'Y', 'T', 'B'}, 0, 8, context);
        });
    }
}