package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class OptionLLM_Test {

    /**
     * Test to ensure that when optionalArg is true and argCount is UNINITIALIZED,
     * argCount is set to 1.
     */
    @Test
    public void testOptionalArgSetsArgCount() {
        Option option = Option.builder("a").optionalArg(true).build();
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs());
    }

    /**
     * Test to ensure that when optionalArg is true and argCount is already set,
     * argCount is not overridden.
     */
    @Test
    public void testOptionalArgDoesNotOverrideArgCount() {
        Option option = Option.builder("a").numberOfArgs(3).optionalArg(true).build();
        assertTrue(option.hasOptionalArg());
        assertEquals(3, option.getArgs());
    }

    /**
     * Test to ensure that when optionalArg is false, argCount is set to UNINITIALIZED.
     */
    @Test
    public void testOptionalArgFalseSetsArgCountUninitialized() {
        Option option = Option.builder("a").optionalArg(false).build();
        assertFalse(option.hasOptionalArg());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
    }
}