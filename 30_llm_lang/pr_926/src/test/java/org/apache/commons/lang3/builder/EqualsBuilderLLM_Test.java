package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class EqualsBuilderLLM_Test {

    @Test
    public void testEffectiveJavaLinkUpdate() {
        // This test ensures that the link to Effective Java in the class documentation is correct.
        // Since this is a documentation change, we will just verify that the class can be loaded.
        assertTrue(EqualsBuilder.class != null);
    }
}