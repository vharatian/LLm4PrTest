package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
     * Test to ensure that the cleanup method correctly processes input strings.
     */
    @Test
    public void testCleanupMethod() {
        DaitchMokotoffSoundex encoder = new DaitchMokotoffSoundex();
        // Test cleanup with spaces and mixed case
        assertEquals("washington", encoder.cleanup(" \t\n\r Washington \t\n\r "));
        // Test cleanup with accented characters and folding
        assertEquals("strassburg", encoder.cleanup("Straßburg"));
        // Test cleanup with non-folding mode
        DaitchMokotoffSoundex nonFoldingEncoder = new DaitchMokotoffSoundex(false);
        assertEquals("straßburg", nonFoldingEncoder.cleanup("Straßburg"));
    }
}