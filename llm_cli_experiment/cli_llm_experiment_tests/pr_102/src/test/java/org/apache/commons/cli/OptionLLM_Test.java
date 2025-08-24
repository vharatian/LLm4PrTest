package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class OptionLLM_Test {

    @Test
    public void testGetValuesWithNoValues() {
        Option option = new Option("f", null);
        option.setArgs(Option.UNLIMITED_VALUES);
        assertNull(option.getValues());
    }

    @Test
    public void testGetValuesWithValues() {
        Option option = new Option("f", null);
        option.setArgs(Option.UNLIMITED_VALUES);
        option.addValueForProcessing("value1");
        option.addValueForProcessing("value2");
        assertArrayEquals(new String[]{"value1", "value2"}, option.getValues());
    }
}