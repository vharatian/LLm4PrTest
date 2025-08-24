package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class OptionLLM_Test {

    @Test
    public void testRequiredFlagCannotBeChanged() {
        // Create an Option instance
        Option option = Option.builder("a").required(true).build();
        assertTrue(option.isRequired());

        // Attempt to change the required flag
        option.setRequired(false);
        assertTrue(option.isRequired()); // The required flag should remain true

        // Create another Option instance with required flag set to false
        Option option2 = Option.builder("b").required(false).build();
        assertFalse(option2.isRequired());

        // Attempt to change the required flag
        option2.setRequired(true);
        assertFalse(option2.isRequired()); // The required flag should remain false
    }
}