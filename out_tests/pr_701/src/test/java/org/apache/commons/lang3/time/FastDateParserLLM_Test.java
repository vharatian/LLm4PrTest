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

public class FastDateParserLLM_Test {

    private static final String yMdHmsSZ = "yyyy-MM-dd'T'HH:mm:ss.SSS Z";
    private static final TimeZone NEW_YORK = TimeZone.getTimeZone("America/New_York");
    private static final Locale US = Locale.US;

    private DateParser getInstance(final String format, final TimeZone timeZone, final Locale locale) {
        return new FastDateParser(format, timeZone, locale, null);
    }

    @Test
    public void testTimeZoneStrategyFinalFields() throws ParseException {
        final String formatStub = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        final String dateStub = "2001-02-04T12:08:56.235";

        final Calendar cal = Calendar.getInstance(NEW_YORK);
        cal.clear();
        cal.set(2001, Calendar.FEBRUARY, 4, 12, 8, 56);
        cal.set(Calendar.MILLISECOND, 235);

        DateParser parser = getInstance(formatStub + "X", NEW_YORK, US);
        assertEquals(cal.getTime(), parser.parse(dateStub + "-05").getTime());

        parser = getInstance(formatStub + "XX", NEW_YORK, US);
        assertEquals(cal.getTime(), parser.parse(dateStub + "-0500").getTime());

        parser = getInstance(formatStub + "XXX", NEW_YORK, US);
        assertEquals(cal.getTime(), parser.parse(dateStub + "-05:00").getTime());
    }

    @ParameterizedTest
    @MethodSource("org.apache.commons.lang3.time.FastDateParserTest#dateParserParameters")
    public void testTimeZoneStrategyInvalidTimeZone(final TriFunction<String, TimeZone, Locale, DateParser> dpProvider) {
        final DateParser parser = getInstance("yyyy-MM-dd'T'HH:mm:ss.SSS Z", NEW_YORK, US);
        assertThrows(ParseException.class, () -> parser.parse("2001-02-04T12:08:56.235 INVALID"));
    }
}