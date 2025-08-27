package org.apache.commons.text.similarity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntersectionResultLLM_Test {

    @Test
    public void testNewIntersectionResult_WithInvalidIntersectionMessage() {
        final int sizeA = 5;
        final int sizeB = 5;
        final int intersection = 6;
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new IntersectionResult(sizeA, sizeB, intersection));
        Assertions.assertEquals("Invalid intersection of A and B: " + intersection, exception.getMessage());
    }
}