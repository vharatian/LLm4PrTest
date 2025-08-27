package org.apache.commons.codec.language;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.commons.codec.AbstractStringEncoderTest;
import org.apache.commons.codec.EncoderException;
import org.junit.jupiter.api.Test;

public class RefinedSoundexLLM_Test extends AbstractStringEncoderTest<RefinedSoundex> {

    @Override
    protected RefinedSoundex createStringEncoder() {
        return new RefinedSoundex();
    }

    @Test
    public void testGetMappingCodeOutOfBounds() {
        // Test for character before 'A'
        final char codeBeforeA = this.getStringEncoder().getMappingCode('@');
        assertEquals(0, codeBeforeA, "Code for '@' should be zero");

        // Test for character after 'Z'
        final char codeAfterZ = this.getStringEncoder().getMappingCode('[');
        assertEquals(0, codeAfterZ, "Code for '[' should be zero");
    }

    @Test
    public void testGetMappingCodeValidLetters() {
        // Test for valid letters within the bounds
        final char codeA = this.getStringEncoder().getMappingCode('A');
        assertEquals('0', codeA, "Code for 'A' should be '0'");

        final char codeZ = this.getStringEncoder().getMappingCode('Z');
        assertEquals('5', codeZ, "Code for 'Z' should be '5'");
    }
}