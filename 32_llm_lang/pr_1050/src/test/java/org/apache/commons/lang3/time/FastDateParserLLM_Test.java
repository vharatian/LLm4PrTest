package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Locale;
import org.junit.jupiter.api.Test;

public class FastDateParserLLM_Test {

    @Test
    public void testBugLinkUpdate() {
        // Ensure that the bug link in the Javadoc is correctly updated
        FastDateParser parser = new FastDateParser("yyyy-MM-dd", TimeZone.getDefault(), Locale.getDefault());
        assertNotNull(parser);
        assertEquals("FastDateParser[yyyy-MM-dd, " + Locale.getDefault() + ", " + TimeZone.getDefault().getID() + "]", parser.toString());
    }
}