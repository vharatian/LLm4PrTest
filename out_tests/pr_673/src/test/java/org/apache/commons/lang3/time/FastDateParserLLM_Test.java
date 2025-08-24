package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;

public class FastDateParserLLM_Test {

    @Test
    public void testCenturyStartYearCalculation() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        Locale locale = Locale.US;

        // Test with centuryStart provided
        Calendar centuryStartCal = Calendar.getInstance(timeZone, locale);
        centuryStartCal.set(2000, Calendar.JANUARY, 1);
        Date centuryStart = centuryStartCal.getTime();
        FastDateParser parser = new FastDateParser("yyyy", timeZone, locale, centuryStart);
        assertEquals(2000, parser.century);
        assertEquals(0, parser.startYear);

        // Test without centuryStart and non-Japanese locale
        parser = new FastDateParser("yyyy", timeZone, locale, null);
        Calendar currentCal = Calendar.getInstance(timeZone, locale);
        int expectedCenturyStartYear = currentCal.get(Calendar.YEAR) - 80;
        assertEquals(expectedCenturyStartYear / 100 * 100, parser.century);
        assertEquals(expectedCenturyStartYear - (expectedCenturyStartYear / 100 * 100), parser.startYear);

        // Test without centuryStart and Japanese locale
        parser = new FastDateParser("yyyy", timeZone, FastDateParser.JAPANESE_IMPERIAL, null);
        assertEquals(0, parser.century);
        assertEquals(0, parser.startYear);
    }

    @Test
    public void testParsePositionInitialization() throws ParseException {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        Locale locale = Locale.US;
        FastDateParser parser = new FastDateParser("yyyy-MM-dd", timeZone, locale, null);

        // Test parse with ParsePosition initialization
        String dateStr = "2023-10-05";
        Date expectedDate = new Date(123, 9, 5); // Date(year, month, day) where year is year-1900
        assertEquals(expectedDate, parser.parse(dateStr));

        // Test parseObject with ParsePosition initialization
        assertEquals(expectedDate, parser.parseObject(dateStr));
    }

    @Test
    public void testParseWithJapaneseImperialLocale() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        Locale locale = FastDateParser.JAPANESE_IMPERIAL;
        FastDateParser parser = new FastDateParser("yyyy-MM-dd", timeZone, locale, null);

        // Test parse with date before 1868
        String dateStr = "1867-12-31";
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse(dateStr));
        assertEquals("(The ja_JP_JP locale does not support dates before 1868 AD)\nUnparseable date: \"1867-12-31\"", exception.getMessage());
    }
}