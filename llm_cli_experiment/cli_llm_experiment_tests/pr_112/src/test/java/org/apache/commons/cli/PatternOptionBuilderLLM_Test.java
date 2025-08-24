package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class PatternOptionBuilderLLM_Test {

    @Test
    public void testParsePatternWithConsecutiveOptions() throws Exception {
        // Test pattern with consecutive options without resetting opt to ' '
        final Options options = PatternOptionBuilder.parsePattern("a:b:c");
        final CommandLineParser parser = new PosixParser();
        final CommandLine line = parser.parse(options, new String[] {"-a", "valueA", "-b", "valueB", "-c", "valueC"});
        
        assertEquals("a value", "valueA", line.getOptionValue("a"));
        assertEquals("b value", "valueB", line.getOptionValue("b"));
        assertEquals("c value", "valueC", line.getOptionValue("c"));
    }

    @Test
    public void testParsePatternWithRequiredOption() throws Exception {
        // Test pattern with required option without resetting opt to ' '
        final Options options = PatternOptionBuilder.parsePattern("!a:b:c");
        final CommandLineParser parser = new PosixParser();
        
        try {
            parser.parse(options, new String[] {"-b", "valueB", "-c", "valueC"});
            fail("MissingOptionException wasn't thrown");
        } catch (final MissingOptionException e) {
            assertEquals(1, e.getMissingOptions().size());
            assertTrue(e.getMissingOptions().contains("a"));
        }
    }

    @Test
    public void testParsePatternWithMixedOptions() throws Exception {
        // Test pattern with mixed options without resetting opt to ' '
        final Options options = PatternOptionBuilder.parsePattern("a:b!c");
        final CommandLineParser parser = new PosixParser();
        final CommandLine line = parser.parse(options, new String[] {"-a", "valueA", "-b", "valueB", "-c", "valueC"});
        
        assertEquals("a value", "valueA", line.getOptionValue("a"));
        assertEquals("b value", "valueB", line.getOptionValue("b"));
        assertEquals("c value", "valueC", line.getOptionValue("c"));
    }
}