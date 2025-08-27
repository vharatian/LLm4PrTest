package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConstantInitializerLLM_Test {
    private static final Integer VALUE = 42;
    private ConstantInitializer<Integer> init;

    @BeforeEach
    public void setUp() {
        init = new ConstantInitializer<>(VALUE);
    }

    /**
     * Test to verify that the isInitialized method always returns true.
     */
    @Test
    public void testIsInitialized() {
        assertTrue(init.isInitialized(), "isInitialized should always return true");
    }
}