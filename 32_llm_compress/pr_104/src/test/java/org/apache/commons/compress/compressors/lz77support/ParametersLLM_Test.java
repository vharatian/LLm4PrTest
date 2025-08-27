package org.apache.commons.compress.compressors.lz77support;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ParametersLLM_Test {
    @Test
    public void testMinBackReferenceLengthSpellingCorrection() {
        // This test is to ensure that the spelling correction in the comments does not affect functionality
        Parameters p = Parameters.builder(128)
            .withMinBackReferenceLength(4)
            .withMaxBackReferenceLength(10)
            .withMaxOffset(127)
            .withMaxLiteralLength(128)
            .build();
        assertEquals(4, p.getMinBackReferenceLength());
        assertEquals(10, p.getMaxBackReferenceLength());
        assertEquals(127, p.getMaxOffset());
        assertEquals(128, p.getMaxLiteralLength());
    }
}