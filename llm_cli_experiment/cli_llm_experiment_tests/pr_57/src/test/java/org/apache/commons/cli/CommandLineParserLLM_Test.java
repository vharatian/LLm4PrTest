package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandLineParserLLM_Test {

    @Test
    public void testParseWithOptionsAndArguments() throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("a", "alpha", false, "Alpha option");
        String[] arguments = {"-a"};

        CommandLine cmd = parser.parse(options, arguments);

        assertTrue(cmd.hasOption("a"));
    }

    @Test
    public void testParseWithOptionsArgumentsAndStopAtNonOption() throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("a", "alpha", false, "Alpha option");
        String[] arguments = {"-a", "non-option"};

        CommandLine cmd = parser.parse(options, arguments, true);

        assertTrue(cmd.hasOption("a"));
        assertEquals("non-option", cmd.getArgList().get(0));
    }
}