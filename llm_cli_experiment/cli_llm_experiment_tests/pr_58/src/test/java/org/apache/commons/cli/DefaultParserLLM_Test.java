package org.apache.commons.cli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultParserLLM_Test extends ParserTestCase {

    @Override
    @Before
    public void setUp() {
        super.setUp();
        parser = new DefaultParser();
    }

    @Test
    public void testBuilderWithAllowPartialMatching() {
        DefaultParser parser = DefaultParser.builder()
            .setAllowPartialMatching(false)
            .build();
        assertFalse(parser.allowPartialMatching);
    }

    @Test
    public void testBuilderWithStripLeadingAndTrailingQuotes() {
        DefaultParser parser = DefaultParser.builder()
            .setStripLeadingAndTrailingQuotes(true)
            .build();
        assertTrue(parser.stripLeadingAndTrailingQuotes);
    }

    @Test
    public void testBuilderWithNullStripLeadingAndTrailingQuotes() {
        DefaultParser parser = DefaultParser.builder()
            .setStripLeadingAndTrailingQuotes(null)
            .build();
        assertNull(parser.stripLeadingAndTrailingQuotes);
    }

    @Test
    public void testHandleTokenWithStrippingQuotes() throws ParseException {
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().build());
        parser = DefaultParser.builder()
            .setStripLeadingAndTrailingQuotes(true)
            .build();
        CommandLine cmd = parser.parse(options, new String[]{"-o", "\"value\""});
        assertEquals("value", cmd.getOptionValue("o"));
    }

    @Test
    public void testHandleTokenWithoutStrippingQuotes() throws ParseException {
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().build());
        parser = DefaultParser.builder()
            .setStripLeadingAndTrailingQuotes(false)
            .build();
        CommandLine cmd = parser.parse(options, new String[]{"-o", "\"value\""});
        assertEquals("\"value\"", cmd.getOptionValue("o"));
    }

    @Test
    public void testHandleTokenWithDefaultStrippingQuotes() throws ParseException {
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().build());
        parser = DefaultParser.builder().build();
        CommandLine cmd = parser.parse(options, new String[]{"-o", "\"value\""});
        assertEquals("value", cmd.getOptionValue("o"));
    }
}