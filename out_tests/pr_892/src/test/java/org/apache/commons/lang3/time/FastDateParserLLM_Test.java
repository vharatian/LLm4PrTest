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

    static final String DATE_PARSER_PARAMETERS = "dateParserParameters";
    private static final TimeZone NEW_YORK = TimeZone.getTimeZone("America/New_York");

    private DateParser getInstance(final String format, final TimeZone timeZone, final Locale locale) {
        return new FastDateParser(format, timeZone, locale, null);
    }

    @ParameterizedTest
    @MethodSource(FastDateParserTest.DATE_PARSER_PARAMETERS)
    public void testMonthParsingWithL(final TriFunction<String, TimeZone, Locale, DateParser> dpProvider) throws ParseException {
        final Calendar cal = Calendar.getInstance(NEW_YORK, Locale.US);
        cal.clear();
        cal.set(2023, Calendar.APRIL, 1);

        final DateParser parser = getInstance(dpProvider, "yyyy-LL-dd", NEW_YORK, Locale.US);
        assertEquals(cal.getTime(), parser.parse("2023-04-01"));

        cal.set(2023, Calendar.DECEMBER, 1);
        assertEquals(cal.getTime(), parser.parse("2023-12-01"));
    }

    @ParameterizedTest
    @MethodSource(FastDateParserTest.DATE_PARSER_PARAMETERS)
    public void testMonthParsingWithLLL(final TriFunction<String, TimeZone, Locale, DateParser> dpProvider) throws ParseException {
        final Calendar cal = Calendar.getInstance(NEW_YORK, Locale.US);
        cal.clear();
        cal.set(2023, Calendar.APRIL, 1);

        final DateParser parser = getInstance(dpProvider, "yyyy-LLL-dd", NEW_YORK, Locale.US);
        assertEquals(cal.getTime(), parser.parse("2023-Apr-01"));

        cal.set(2023, Calendar.DECEMBER, 1);
        assertEquals(cal.getTime(), parser.parse("2023-Dec-01"));
    }

    @ParameterizedTest
    @MethodSource(FastDateParserTest.DATE_PARSER_PARAMETERS)
    public void testMonthParsingWithLLLL(final TriFunction<String, TimeZone, Locale, DateParser> dpProvider) throws ParseException {
        final Calendar cal = Calendar.getInstance(NEW_YORK, Locale.US);
        cal.clear();
        cal.set(2023, Calendar.APRIL, 1);

        final DateParser parser = getInstance(dpProvider, "yyyy-LLLL-dd", NEW_YORK, Locale.US);
        assertEquals(cal.getTime(), parser.parse("2023-April-01"));

        cal.set(2023, Calendar.DECEMBER, 1);
        assertEquals(cal.getTime(), parser.parse("2023-December-01"));
    }

    @Test
    public void testInvalidMonthPattern() {
        assertThrows(IllegalArgumentException.class, () -> getInstance("yyyy-LLLLL-dd", NEW_YORK, Locale.US));
    }
}