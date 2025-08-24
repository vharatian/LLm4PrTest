package org.apache.commons.cli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GnuParserLLM_Test extends ParserTestCase {

    @Override
    @Before
    public void setUp() {
        super.setUp();
        parser = new GnuParser();
    }

    @Test
    public void testFlattenWithDoubleDash() throws Exception {
        Options options = new Options();
        options.addOption("a", false, "Option A");
        String[] args = new String[] { "--", "arg1", "arg2" };
        String[] expected = new String[] { "--", "arg1", "arg2" };
        String[] result = parser.flatten(options, args, false);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlattenWithSingleDash() throws Exception {
        Options options = new Options();
        options.addOption("a", false, "Option A");
        String[] args = new String[] { "-", "arg1", "arg2" };
        String[] expected = new String[] { "-", "arg1", "arg2" };
        String[] result = parser.flatten(options, args, false);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlattenWithOptionAndArgument() throws Exception {
        Options options = new Options();
        options.addOption("a", true, "Option A");
        String[] args = new String[] { "-a", "value" };
        String[] expected = new String[] { "-a", "value" };
        String[] result = parser.flatten(options, args, false);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlattenWithOptionAndEqualArgument() throws Exception {
        Options options = new Options();
        options.addOption("a", true, "Option A");
        String[] args = new String[] { "-a=value" };
        String[] expected = new String[] { "-a", "value" };
        String[] result = parser.flatten(options, args, false);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlattenWithUnknownOption() throws Exception {
        Options options = new Options();
        options.addOption("a", false, "Option A");
        String[] args = new String[] { "-b" };
        String[] expected = new String[] { "-b" };
        String[] result = parser.flatten(options, args, false);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlattenWithStopAtNonOption() throws Exception {
        Options options = new Options();
        options.addOption("a", false, "Option A");
        String[] args = new String[] { "-a", "arg1", "-b", "arg2" };
        String[] expected = new String[] { "-a", "arg1", "-b", "arg2" };
        String[] result = parser.flatten(options, args, true);
        assertArrayEquals(expected, result);
    }
}