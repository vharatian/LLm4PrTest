package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class OptionBuilderLLM_Test {

    @Test
    public void testCreateOptionWithValidStringOpt() {
        final Option option = OptionBuilder.withDescription("test description").create("t");
        assertEquals("t", option.getOpt());
        assertEquals("test description", option.getDescription());
        assertFalse(option.hasArg());
    }

    @Test
    public void testCreateOptionWithInvalidStringOpt() {
        try {
            OptionBuilder.withDescription("test description").create("");
            fail("IllegalArgumentException expected");
        } catch (final IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testCreateOptionWithValidCharOpt() {
        final Option option = OptionBuilder.withDescription("test description").create('t');
        assertEquals("t", option.getOpt());
        assertEquals("test description", option.getDescription());
        assertFalse(option.hasArg());
    }

    @Test
    public void testCreateOptionWithInvalidCharOpt() {
        try {
            OptionBuilder.withDescription("test description").create(' ');
            fail("IllegalArgumentException expected");
        } catch (final IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testResetAfterException() {
        try {
            OptionBuilder.withDescription("test description").create(' ');
            fail("IllegalArgumentException expected");
        } catch (final IllegalArgumentException e) {
            // expected
        }
        assertNull("Description should be reset", OptionBuilder.create('x').getDescription());
    }
}