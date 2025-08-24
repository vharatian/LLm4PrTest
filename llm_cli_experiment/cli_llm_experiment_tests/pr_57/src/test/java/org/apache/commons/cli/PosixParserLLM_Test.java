package org.apache.commons.cli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PosixParserLLM_Test extends ParserTestCase {

    @Override
    @SuppressWarnings("deprecation")
    @Before
    public void setUp() {
        super.setUp();
        parser = new PosixParser();
    }

    @Test
    public void testLicenseHeaderChange() {
        // This test ensures that the license header change does not affect functionality.
        // Since the license header change is a non-functional change, we can run a basic functionality test.
        try {
            String[] args = {"-a", "value"};
            Options options = new Options();
            options.addOption("a", true, "option a");
            CommandLine cmd = parser.parse(options, args);
            assertTrue(cmd.hasOption("a"));
            assertEquals("value", cmd.getOptionValue("a"));
        } catch (ParseException e) {
            fail("Parsing failed: " + e.getMessage());
        }
    }
}