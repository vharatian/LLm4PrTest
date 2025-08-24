package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Calendar;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.DefaultLocale;
import org.junitpioneer.jupiter.DefaultTimeZone;

public class FastDatePrinterLLM_Test {

    private static final Locale SWEDEN = new Locale("sv", "SE");

    private DatePrinter getInstance(final String format, final TimeZone timeZone, final Locale locale) {
        return new FastDatePrinter(format, timeZone, locale);
    }

    @Test
    public void testStandaloneMonthText() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        DatePrinter printer = getInstance("LLLL", TimeZone.getDefault(), SWEDEN);
        assertEquals("januari", printer.format(cal));
        printer = getInstance("LLL", TimeZone.getDefault(), SWEDEN);
        assertEquals("jan", printer.format(cal));
    }

    @Test
    public void testStandaloneMonthNumber() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        DatePrinter printer = getInstance("LL", TimeZone.getDefault(), SWEDEN);
        assertEquals("01", printer.format(cal));
        printer = getInstance("L", TimeZone.getDefault(), SWEDEN);
        assertEquals("1", printer.format(cal));
    }

    @Test
    public void testInvalidPattern() {
        assertThrows(IllegalArgumentException.class, () -> getInstance("LLLLL", TimeZone.getDefault(), SWEDEN));
    }
}