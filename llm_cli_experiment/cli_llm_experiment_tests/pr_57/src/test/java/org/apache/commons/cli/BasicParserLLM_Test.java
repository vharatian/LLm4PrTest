package org.apache.commons.cli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class BasicParserLLM_Test extends ParserTestCase {

    @Override
    @Before
    public void setUp() {
        super.setUp();
        parser = new BasicParser();
    }

    @Test
    public void testFlattenReturnsArguments() {
        String[] args = {"-a", "value1", "--option", "value2"};
        Options options = new Options();
        boolean stopAtNonOption = false;

        String[] result = parser.flatten(options, args, stopAtNonOption);

        assertArrayEquals(args, result);
    }
}