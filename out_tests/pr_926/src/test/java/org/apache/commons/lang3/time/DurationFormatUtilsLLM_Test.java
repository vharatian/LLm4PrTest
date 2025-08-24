package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DurationFormatUtilsLLM_Test {

    @Test
    public void testJodaTimeLinkUpdate() {
        // This test ensures that the Joda-Time link in the Javadoc is correct.
        String expectedLink = "https://www.joda.org/joda-time/";
        String actualLink = DurationFormatUtils.class.getDeclaredMethods()[0].getAnnotation(Deprecated.class).toString();
        assertEquals(expectedLink, actualLink);
    }
}