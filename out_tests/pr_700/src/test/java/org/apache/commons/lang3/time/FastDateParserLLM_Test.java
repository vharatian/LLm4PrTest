package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;

public class FastDateParserLLM_Test {

    @Test
    public void testCenturyStartYearWithNullCenturyStart() {
        // Test case for when centuryStart is null
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        Locale locale = Locale.US;
        FastDateParser parser = new FastDateParser("yyyy", timeZone, locale, null);

        Calendar definingCalendar = Calendar.getInstance(timeZone, locale);
        int expectedCenturyStartYear = definingCalendar.get(Calendar.YEAR) - 80;
        int expectedCentury = expectedCenturyStartYear / 100 * 100;
        int expectedStartYear = expectedCenturyStartYear - expectedCentury;

        assertEquals(expectedCentury, parser.century);
        assertEquals(expectedStartYear, parser.startYear);
    }

    @Test
    public void testCenturyStartYearWithNonNullCenturyStart() {
        // Test case for when centuryStart is not null
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        Locale locale = Locale.US;
        Calendar centuryStartCalendar = Calendar.getInstance(timeZone, locale);
        centuryStartCalendar.set(1950, Calendar.JANUARY, 1);
        FastDateParser parser = new FastDateParser("yyyy", timeZone, locale, centuryStartCalendar.getTime());

        int expectedCenturyStartYear = 1950;
        int expectedCentury = expectedCenturyStartYear / 100 * 100;
        int expectedStartYear = expectedCenturyStartYear - expectedCentury;

        assertEquals(expectedCentury, parser.century);
        assertEquals(expectedStartYear, parser.startYear);
    }

    @Test
    public void testCenturyStartYearWithJapaneseImperialLocale() {
        // Test case for when locale is JAPANESE_IMPERIAL
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        Locale locale = new Locale("ja", "JP", "JP");
        FastDateParser parser = new FastDateParser("yyyy", timeZone, locale, null);

        int expectedCenturyStartYear = 0;
        int expectedCentury = expectedCenturyStartYear / 100 * 100;
        int expectedStartYear = expectedCenturyStartYear - expectedCentury;

        assertEquals(expectedCentury, parser.century);
        assertEquals(expectedStartYear, parser.startYear);
    }

    @Test
    public void testUnterminatedQuote() {
        // Test case for unterminated quote in pattern
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        Locale locale = Locale.US;
        assertThrows(IllegalArgumentException.class, () -> {
            new FastDateParser("yyyy-MM-dd'T'HH:mm:ss.SSS'Z", timeZone, locale, null);
        });
    }
}