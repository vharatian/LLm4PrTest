package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class EqualsBuilderLLM_Test {

    @Test
    public void testDefaultValues() {
        EqualsBuilder builder = new EqualsBuilder();
        // Ensure default values are set correctly
        assertFalse(builder.isEquals());
        assertFalse(builder.setTestTransients(false).isEquals());
        assertFalse(builder.setTestRecursive(false).isEquals());
    }

    @Test
    public void testSetTestTransients() {
        EqualsBuilder builder = new EqualsBuilder();
        builder.setTestTransients(true);
        assertTrue(builder.setTestTransients(true).isEquals());
    }

    @Test
    public void testSetTestRecursive() {
        EqualsBuilder builder = new EqualsBuilder();
        builder.setTestRecursive(true);
        assertTrue(builder.setTestRecursive(true).isEquals());
    }
}