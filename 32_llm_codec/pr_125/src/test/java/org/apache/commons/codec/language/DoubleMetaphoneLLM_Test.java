package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoubleMetaphoneLLM_Test extends StringEncoderAbstractTest<DoubleMetaphone> {

    @Override
    protected DoubleMetaphone createStringEncoder() {
        return new DoubleMetaphone();
    }

    /**
     * Test the updated isSlavoGermanic method to ensure it correctly identifies Slavo-Germanic names.
     */
    @Test
    public void testIsSlavoGermanic() {
        DoubleMetaphone encoder = new DoubleMetaphone();
        
        // Test cases that should return true
        assertTrue(encoder.isSlavoGermanic("Wagner"));
        assertTrue(encoder.isSlavoGermanic("Kowalski"));
        assertTrue(encoder.isSlavoGermanic("Czerny"));
        assertTrue(encoder.isSlavoGermanic("Witzel"));

        // Test cases that should return false
        assertFalse(encoder.isSlavoGermanic("Smith"));
        assertFalse(encoder.isSlavoGermanic("Johnson"));
        assertFalse(encoder.isSlavoGermanic("Brown"));
        assertFalse(encoder.isSlavoGermanic("Taylor"));
    }
}