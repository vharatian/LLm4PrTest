package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class OptionLLM_Test {

    /**
     * Test the new opt() method in the Builder class.
     */
    @Test
    public void testBuilderOptMethod() {
        Option.Builder builder = Option.builder("a").desc("desc");
        builder.opt("b");
        Option option = builder.build();
        assertEquals("b", option.getOpt());
    }

    /**
     * Test the opt() method with invalid characters.
     */
    @Test
    public void testBuilderOptMethodInvalidCharacters() {
        Option.Builder builder = Option.builder("a").desc("desc");
        assertThrows(IllegalArgumentException.class, () -> builder.opt("invalid opt"));
    }
}