package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HelpFormatterLLM_Test {

    @Test
    public void testRenderOptionsMaxCalculation() {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        options.addOption("a", false, "option a");
        options.addOption("b", false, "option b");
        options.addOption("c", false, "option c");

        StringBuffer sb = new StringBuffer();
        formatter.renderOptions(sb, 80, options, 1, 3);

        // Expected output should have the longest option length calculated correctly
        String expected = " -a   option a\n -b   option b\n -c   option c";
        assertEquals(expected, sb.toString().trim());
    }
}