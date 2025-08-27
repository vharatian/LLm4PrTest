package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Locale;
import org.junit.jupiter.api.Test;

public class CalendarUtilsLLM_Test {

    // No new functionality or modifications were introduced in the diff file.
    // The diff file only removed a redundant Javadoc parameter description.
    // Therefore, no new tests are required to cover this change.
    
    // However, if we want to ensure completeness, we can add a test for the getMonthDisplayNames method directly.
    
    @Test
    public void testGetMonthDisplayNames() {
        final String[] monthNames = CalendarUtils.getInstance(Locale.GERMAN).getMonthDisplayNames(Calendar.LONG);
        assertEquals(12, monthNames.length);
        assertEquals("Januar", monthNames[0]);
        assertEquals("Februar", monthNames[1]);
        assertEquals("März", monthNames[2]);
        assertEquals("April", monthNames[3]);
        assertEquals("Mai", monthNames[4]);
        assertEquals("Juni", monthNames[5]);
        assertEquals("Juli", monthNames[6]);
        assertEquals("August", monthNames[7]);
        assertEquals("September", monthNames[8]);
        assertEquals("Oktober", monthNames[9]);
        assertEquals("November", monthNames[10]);
        assertEquals("Dezember", monthNames[11]);
    }
}