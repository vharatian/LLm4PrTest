package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class HashCodeBuilderLLM_Test {

    @Test
    public void testDefaultConstructorInitialValue() {
        // Test that the default constructor initializes iTotal correctly
        HashCodeBuilder builder = new HashCodeBuilder();
        assertEquals(17, builder.toHashCode());
    }

    @Test
    public void testCustomConstructorInitialValue() {
        // Test that the custom constructor initializes iTotal correctly
        HashCodeBuilder builder = new HashCodeBuilder(19, 41);
        assertEquals(19, builder.toHashCode());
    }

    @Test
    public void testAppendAfterInitialization() {
        // Test that appending values after initialization works correctly
        HashCodeBuilder builder = new HashCodeBuilder(19, 41);
        builder.append(5);
        assertEquals(19 * 41 + 5, builder.toHashCode());
    }
}