package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Locale;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.DefaultLocale;
import org.junitpioneer.jupiter.DefaultTimeZone;

public class FastDateFormatLLM_Test {

    @Test
    public void test_getInstance_String_TimeZone_Locale_CenturyStart() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"), Locale.GERMANY, new Date(0));
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY, new Date(0));
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getDefault(), Locale.GERMANY, new Date(0));
        assertNotSame(format1, format2);
        assertEquals(TimeZone.getTimeZone("Atlantic/Reykjavik"), format1.getTimeZone());
        assertEquals(TimeZone.getDefault(), format2.getTimeZone());
        assertEquals(TimeZone.getDefault(), format3.getTimeZone());
        assertEquals(Locale.GERMANY, format1.getLocale());
        assertEquals(Locale.GERMANY, format2.getLocale());
        assertEquals(Locale.GERMANY, format3.getLocale());
    }

    @Test
    public void test_getInstance_String_TimeZone_Locale_CenturyStart_Null() {
        try {
            FastDateFormat.getInstance("MM/DD/yyyy", null, Locale.GERMANY, new Date(0));
        } catch (NullPointerException e) {
            assertEquals("TimeZone must not be null", e.getMessage());
        }

        try {
            FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault(), null, new Date(0));
        } catch (NullPointerException e) {
            assertEquals("Locale must not be null", e.getMessage());
        }

        try {
            FastDateFormat.getInstance(null, TimeZone.getDefault(), Locale.GERMANY, new Date(0));
        } catch (NullPointerException e) {
            assertEquals("Pattern must not be null", e.getMessage());
        }
    }
}