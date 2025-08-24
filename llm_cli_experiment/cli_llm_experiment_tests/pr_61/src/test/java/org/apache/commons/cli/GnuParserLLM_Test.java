package org.apache.commons.cli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

@SuppressWarnings("deprecation")
public class GnuParserLLM_Test extends ParserTestCase {

    @Override
    @Before
    public void setUp() {
        super.setUp();
        parser = new GnuParser();
    }

    @Test
    public void testFlattenWithEmptyStringArray() throws Exception {
        Options options = new Options();
        String[] arguments = {};
        String[] expected = {};
        String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlattenWithSingleDash() throws Exception {
        Options options = new Options();
        String[] arguments = {"-"};
        String[] expected = {"-"};
        String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlattenWithDoubleDash() throws Exception {
        Options options = new Options();
        String[] arguments = {"--"};
        String[] expected = {"--"};
        String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlattenWithOptionAndValue() throws Exception {
        Options options = new Options();
        options.addOption("a", false, "option a");
        String[] arguments = {"-a", "value"};
        String[] expected = {"-a", "value"};
        String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlattenWithOptionAndEqualsValue() throws Exception {
        Options options = new Options();
        options.addOption("a", true, "option a");
        String[] arguments = {"-a=value"};
        String[] expected = {"-a", "value"};
        String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlattenWithNonOptionArgument() throws Exception {
        Options options = new Options();
        String[] arguments = {"arg"};
        String[] expected = {"arg"};
        String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlattenWithStopAtNonOption() throws Exception {
        Options options = new Options();
        options.addOption("a", false, "option a");
        String[] arguments = {"-a", "arg"};
        String[] expected = {"-a", "arg"};
        String[] result = parser.flatten(options, arguments, true);
        assertArrayEquals(expected, result);
    }
}