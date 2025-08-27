package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class ObjectUtilsLLM_Test {

    @Test
    public void testIsEmptyWithOptional() {
        // Test with Optional.empty()
        Optional<String> emptyOptional = Optional.empty();
        assertTrue(ObjectUtils.isEmpty(emptyOptional));

        // Test with Optional containing a non-empty string
        Optional<String> nonEmptyOptional = Optional.of("test");
        assertFalse(ObjectUtils.isEmpty(nonEmptyOptional));

        // Test with Optional containing an empty string
        Optional<String> optionalWithEmptyString = Optional.of("");
        assertFalse(ObjectUtils.isEmpty(optionalWithEmptyString));
    }

    @Test
    public void testIsNotEmptyWithOptional() {
        // Test with Optional.empty()
        Optional<String> emptyOptional = Optional.empty();
        assertFalse(ObjectUtils.isNotEmpty(emptyOptional));

        // Test with Optional containing a non-empty string
        Optional<String> nonEmptyOptional = Optional.of("test");
        assertTrue(ObjectUtils.isNotEmpty(nonEmptyOptional));

        // Test with Optional containing an empty string
        Optional<String> optionalWithEmptyString = Optional.of("");
        assertTrue(ObjectUtils.isNotEmpty(optionalWithEmptyString));
    }
}