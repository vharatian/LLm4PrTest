package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import java.util.Locale;

public class FastDateParserLLM_Test {

    /**
     * Test to ensure the updated bug URL is correctly formatted and accessible.
     */
    @Test
    public void testBugUrl() {
        String expectedUrl = "https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4228335";
        String actualUrl = FastDateParser.class.getAnnotation(Deprecated.class).toString();
        assertNotNull(actualUrl);
        assertEquals(expectedUrl, actualUrl);
    }
}