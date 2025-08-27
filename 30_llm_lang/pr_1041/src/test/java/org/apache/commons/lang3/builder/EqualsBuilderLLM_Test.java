package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class EqualsBuilderLLM_Test {

    @Test
    public void testEqualsBuilderConstructor() {
        // Test the constructor to ensure it initializes bypassReflectionClasses with a size of 1
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        assertEquals(1, equalsBuilder.bypassReflectionClasses.size());
        assertTrue(equalsBuilder.bypassReflectionClasses.contains(String.class));
    }
}