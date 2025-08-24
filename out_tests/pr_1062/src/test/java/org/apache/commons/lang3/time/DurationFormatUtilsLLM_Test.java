package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.DefaultTimeZone;

public class DurationFormatUtilsLLM_Test {

    private void assertEqualDuration(final String expected, final int[] start, final int[] end, final String format) {
        final Calendar cal1 = Calendar.getInstance();
        cal1.set(start[0], start[1], start[2], start[3], start[4], start[5]);
        cal1.set(Calendar.MILLISECOND, 0);
        final Calendar cal2 = Calendar.getInstance();
        cal2.set(end[0], end[1], end[2], end[3], end[4], end[5]);
        cal2.set(Calendar.MILLISECOND, 0);
        final long milli1 = cal1.getTime().getTime();
        final long milli2 = cal2.getTime().getTime();
        final String result = DurationFormatUtils.formatPeriod(milli1, milli2, format);
        assertEquals(expected, result);
    }

    @Test
    public void testOptionalTokens() {
        long duration = 0;
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[y]"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[M]"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[d]"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[H]"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[m]"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[s]"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[S]"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[SSSS]"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[yyyy]"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[yyMM]"));

        duration = 60 * 1000;
        assertEquals("1", DurationFormatUtils.formatDuration(duration, "[m]"));
        assertEquals("60", DurationFormatUtils.formatDuration(duration, "[s]"));
        assertEquals("60000", DurationFormatUtils.formatDuration(duration, "[S]"));
        assertEquals("01:00", DurationFormatUtils.formatDuration(duration, "[mm]:ss"));
    }

    @Test
    public void testOptionalTokensWithLiterals() {
        long duration = 0;
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[y 'years']"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[M 'months']"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[d 'days']"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[H 'hours']"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[m 'minutes']"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[s 'seconds']"));
        assertEquals("", DurationFormatUtils.formatDuration(duration, "[S 'milliseconds']"));

        duration = 60 * 1000;
        assertEquals("1 minute", DurationFormatUtils.formatDuration(duration, "[m 'minute']"));
        assertEquals("60 seconds", DurationFormatUtils.formatDuration(duration, "[s 'seconds']"));
        assertEquals("60000 milliseconds", DurationFormatUtils.formatDuration(duration, "[S 'milliseconds']"));
        assertEquals("01:00", DurationFormatUtils.formatDuration(duration, "[mm]:ss"));
    }

    @Test
    public void testNestedOptionalTokens() {
        assertThrows(IllegalArgumentException.class, () -> DurationFormatUtils.formatDuration(0, "[[y]]"));
        assertThrows(IllegalArgumentException.class, () -> DurationFormatUtils.formatDuration(0, "[y[M]]"));
    }

    @Test
    public void testUnmatchedOptionalTokens() {
        assertThrows(IllegalArgumentException.class, () -> DurationFormatUtils.formatDuration(0, "[y"));
        assertThrows(IllegalArgumentException.class, () -> DurationFormatUtils.formatDuration(0, "y]"));
    }

    @Test
    @DefaultTimeZone("GMT")
    public void testOptionalTokensInPeriod() {
        final Calendar cal1 = Calendar.getInstance();
        cal1.set(2020, Calendar.JANUARY, 1, 0, 0, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        final Calendar cal2 = Calendar.getInstance();
        cal2.set(2021, Calendar.JANUARY, 1, 0, 0, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        final long milli1 = cal1.getTime().getTime();
        final long milli2 = cal2.getTime().getTime();

        assertEquals("1", DurationFormatUtils.formatPeriod(milli1, milli2, "[y]"));
        assertEquals("1 year", DurationFormatUtils.formatPeriod(milli1, milli2, "[y 'year']"));
        assertEquals("12", DurationFormatUtils.formatPeriod(milli1, milli2, "[M]"));
        assertEquals("12 months", DurationFormatUtils.formatPeriod(milli1, milli2, "[M 'months']"));
    }
}