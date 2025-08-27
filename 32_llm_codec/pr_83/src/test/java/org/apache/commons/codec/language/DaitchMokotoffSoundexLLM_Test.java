package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.Assert;
import org.junit.Test;

public class DaitchMokotoffSoundexLLM_Test extends StringEncoderAbstractTest<DaitchMokotoffSoundex> {

    @Override
    protected DaitchMokotoffSoundex createStringEncoder() {
        return new DaitchMokotoffSoundex();
    }

    private String soundex(final String source) {
        return getStringEncoder().soundex(source);
    }

    private String encode(final String source) {
        return getStringEncoder().encode(source);
    }

    /**
     * Test to ensure that the change in the condition for checking if the next character is a vowel works correctly.
     * Specifically, this test targets the change from using a ternary operator to a logical AND operator.
     */
    @Test
    public void testNextCharIsVowelCondition() {
        // Test case where the next character is a vowel and should be correctly identified
        Assert.assertEquals("097400", soundex("AUERBACH")); // 'U' is a vowel after 'A'
        // Test case where the next character is not a vowel and should be correctly identified
        Assert.assertEquals("097400", soundex("OHRBACH")); // 'H' is not a vowel after 'O'
        // Test case where the next character is at the end of the string and should not cause an error
        Assert.assertEquals("097400", soundex("A")); // Single character, no next character
    }
}