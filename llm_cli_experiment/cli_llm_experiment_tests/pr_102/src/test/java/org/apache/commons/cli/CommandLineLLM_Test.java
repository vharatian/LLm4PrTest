package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class CommandLineLLM_Test {

    @Test
    public void testGetOptionValuesWithEmptyStringArray() {
        CommandLine cmd = new CommandLine();
        Option option = Option.builder("a").hasArgs().build();
        cmd.addOption(option);
        assertNull(cmd.getOptionValues(option));

        cmd.addOption(Option.builder("b").hasArgs().build());
        assertArrayEquals(new String[0], cmd.getOptionValues(option));
    }
}