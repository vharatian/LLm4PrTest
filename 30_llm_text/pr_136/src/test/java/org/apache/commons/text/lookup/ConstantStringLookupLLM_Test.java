package org.apache.commons.text.lookup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConstantStringLookupLLM_Test {

    private ConstantStringLookup stringLookup;

    @AfterEach
    public void afterEach() {
        ConstantStringLookup.clear();
    }

    @BeforeEach
    public void beforeEach() {
        stringLookup = ConstantStringLookup.INSTANCE;
    }

    /**
     * Test to ensure that the CONSTANT_CACHE is correctly cleared.
     */
    @Test
    public void testClearCache() {
        // Populate the cache
        stringLookup.lookup(variable("FIELD"));
        Assertions.assertNotNull(stringLookup.lookup(variable("FIELD")), "Cache should contain the value");

        // Clear the cache
        ConstantStringLookup.clear();
        Assertions.assertNull(stringLookup.lookup(variable("FIELD")), "Cache should be cleared");
    }

    /**
     * Test to ensure that the CONSTANT_CACHE is correctly used.
     */
    @Test
    public void testCacheUsage() {
        // First lookup should populate the cache
        String firstLookup = stringLookup.lookup(variable("FIELD"));
        Assertions.assertNotNull(firstLookup, "First lookup should return a value");

        // Second lookup should retrieve from cache
        String secondLookup = stringLookup.lookup(variable("FIELD"));
        Assertions.assertEquals(firstLookup, secondLookup, "Second lookup should return the cached value");
    }

    private String variable(final String field) {
        return getClass().getName() + '.' + field;
    }
}