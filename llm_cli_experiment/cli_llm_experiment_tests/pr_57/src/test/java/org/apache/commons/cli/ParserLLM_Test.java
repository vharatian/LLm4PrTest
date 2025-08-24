package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParserLLM_Test {

    @Test
    public void testParseWithNullArguments() throws ParseException {
        Parser parser = new ConcreteParser();
        Options options = new Options();
        CommandLine cmd = parser.parse(options, null);
        assertNotNull(cmd);
        assertEquals(0, cmd.getArgs().length);
    }

    @Test
    public void testParseWithEmptyArguments() throws ParseException {
        Parser parser = new ConcreteParser();
        Options options = new Options();
        CommandLine cmd = parser.parse(options, new String[0]);
        assertNotNull(cmd);
        assertEquals(0, cmd.getArgs().length);
    }

    @Test
    public void testParseWithStopAtNonOption() throws ParseException {
        Parser parser = new ConcreteParser();
        Options options = new Options();
        options.addOption("a", false, "Option a");
        String[] args = {"-a", "non-option"};
        CommandLine cmd = parser.parse(options, args, true);
        assertNotNull(cmd);
        assertTrue(cmd.hasOption("a"));
        assertEquals(1, cmd.getArgs().length);
        assertEquals("non-option", cmd.getArgs()[0]);
    }

    @Test
    public void testProcessPropertiesWithNullProperties() throws ParseException {
        Parser parser = new ConcreteParser();
        Options options = new Options();
        parser.setOptions(options);
        parser.processProperties(null);
        // No exception should be thrown
    }

    @Test(expected = UnrecognizedOptionException.class)
    public void testProcessPropertiesWithUnrecognizedOption() throws ParseException {
        Parser parser = new ConcreteParser();
        Options options = new Options();
        parser.setOptions(options);
        Properties properties = new Properties();
        properties.setProperty("unrecognized", "value");
        parser.processProperties(properties);
    }

    @Test
    public void testProcessPropertiesWithRecognizedOption() throws ParseException {
        Parser parser = new ConcreteParser();
        Options options = new Options();
        Option option = new Option("a", true, "Option a");
        options.addOption(option);
        parser.setOptions(options);
        Properties properties = new Properties();
        properties.setProperty("a", "value");
        parser.processProperties(properties);
        assertTrue(parser.cmd.hasOption("a"));
        assertEquals("value", parser.cmd.getOptionValue("a"));
    }

    @Test(expected = MissingOptionException.class)
    public void testCheckRequiredOptions() throws ParseException {
        Parser parser = new ConcreteParser();
        Options options = new Options();
        Option option = new Option("a", true, "Option a");
        option.setRequired(true);
        options.addOption(option);
        parser.setOptions(options);
        parser.checkRequiredOptions();
    }

    // Concrete implementation of the abstract Parser class for testing purposes
    private static class ConcreteParser extends Parser {
        @Override
        protected String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
            return arguments;
        }
    }
}