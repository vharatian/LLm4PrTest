package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.apache.commons.lang3.function.TriFunction;

public class FastDateParserLLM_Test {

    private static final TimeZone NEW_YORK = TimeZone.getTimeZone("America/New_York");

    private DateParser getInstance(final TriFunction<String, TimeZone, Locale, DateParser> dpProvider, final String format, final TimeZone timeZone, final Locale locale) {
        return dpProvider.apply(format, timeZone, locale);
    }

    @ParameterizedTest
    @MethodSource(FastDateParserTest.DATE_PARSER_PARAMETERS)
    public void testAmPmWithDayPeriods(final TriFunction<String, TimeZone, Locale, DateParser> dpProvider) throws ParseException {
        final Calendar cal = Calendar.getInstance(NEW_YORK, Locale.US);
        cal.clear();

        final DateParser parser = getInstance(dpProvider, "yyyy-MM-dd hh a mm:ss", NEW_YORK, Locale.US);
        
        // Test for AM period
        cal.set(2023, Calendar.OCTOBER, 10, 0, 30, 0);
        assertEquals(cal.getTime(), parser.parse("2023-10-10 12 AM 30:00"));

        // Test for PM period
        cal.set(2023, Calendar.OCTOBER, 10, 12, 30, 0);
        assertEquals(cal.getTime(), parser.parse("2023-10-10 12 PM 30:00"));

        // Test for invalid period (should throw ParseException)
        assertThrows(ParseException.class, () -> parser.parse("2023-10-10 12 XYZ 30:00"));
    }
}