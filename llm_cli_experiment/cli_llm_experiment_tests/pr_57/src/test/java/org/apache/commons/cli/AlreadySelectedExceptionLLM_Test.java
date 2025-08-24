package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlreadySelectedExceptionLLM_Test {

    @Test
    public void testConstructorWithMessage() {
        String message = "Test message";
        AlreadySelectedException exception = new AlreadySelectedException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testConstructorWithGroupAndOption() {
        OptionGroup group = new OptionGroup();
        Option option = new Option("a", "optionA", false, "Option A");
        group.setSelected(option.getKey());
        AlreadySelectedException exception = new AlreadySelectedException(group, option);
        assertEquals("The option 'a' was specified but an option from this group has already been selected: 'a'", exception.getMessage());
        assertEquals(group, exception.getOptionGroup());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testGetOptionGroup() {
        OptionGroup group = new OptionGroup();
        Option option = new Option("b", "optionB", false, "Option B");
        AlreadySelectedException exception = new AlreadySelectedException(group, option);
        assertEquals(group, exception.getOptionGroup());
    }

    @Test
    public void testGetOption() {
        Option option = new Option("c", "optionC", false, "Option C");
        OptionGroup group = new OptionGroup();
        AlreadySelectedException exception = new AlreadySelectedException(group, option);
        assertEquals(option, exception.getOption());
    }
}