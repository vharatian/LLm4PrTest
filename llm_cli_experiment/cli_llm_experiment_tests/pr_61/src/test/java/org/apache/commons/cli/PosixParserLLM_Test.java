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
    public void testFlattenReturnsEmptyStringArray() throws Exception {
        Options options = new Options();
        String[] arguments = new String[]{};
        String[] result = parser.flatten(options, arguments, true);
        assertArrayEquals(Util.EMPTY_STRING_ARRAY, result);
    }

    @Test
    public void testFlattenWithNonEmptyArguments() throws Exception {
        Options options = new Options();
        options.addOption("a", false, "Option a");
        options.addOption("b", true, "Option b");

        String[] arguments = new String[]{"-a", "-b", "value"};
        String[] expected = new String[]{"-a", "-b", "value"};
        String[] result = parser.flatten(options, arguments, true);

        assertArrayEquals(expected, result);
    }
}