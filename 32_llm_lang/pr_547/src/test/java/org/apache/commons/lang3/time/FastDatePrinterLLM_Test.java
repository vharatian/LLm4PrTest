package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Locale;
import org.junit.jupiter.api.Test;

public class FastDatePrinterLLM_Test {

    private static final String YYYY = "yyyy";
    private static final Locale SWEDEN = new Locale("sv", "SE");

    DatePrinter getInstance(final String format) {
        return getInstance(format, TimeZone.getDefault(), Locale.getDefault());
    }

    protected DatePrinter getInstance(final String format, final TimeZone timeZone, final Locale locale) {
        return new FastDatePrinter(format, timeZone, locale);
    }

    @Test
    public void testYearPadding() {
        final Calendar cal = Calendar.getInstance();
        final DatePrinter format = getInstance(YYYY);

        // Test years with different lengths
        cal.set(1, Calendar.JANUARY, 1);
        assertEquals("0001", format.format(cal));

        cal.set(10, Calendar.JANUARY, 1);
        assertEquals("0010", format.format(cal));

        cal.set(100, Calendar.JANUARY, 1);
        assertEquals("0100", format.format(cal));

        cal.set(999, Calendar.JANUARY, 1);
        assertEquals("0999", format.format(cal));

        cal.set(1000, Calendar.JANUARY, 1);
        assertEquals("1000", format.format(cal));

        cal.set(2023, Calendar.JANUARY, 1);
        assertEquals("2023", format.format(cal));
    }

    @Test
    public void testYearPaddingWithLocale() {
        final Calendar cal = Calendar.getInstance();
        final DatePrinter format = getInstance(YYYY, SWEDEN);

        // Test years with different lengths in Swedish locale
        cal.set(1, Calendar.JANUARY, 1);
        assertEquals("0001", format.format(cal));

        cal.set(10, Calendar.JANUARY, 1);
        assertEquals("0010", format.format(cal));

        cal.set(100, Calendar.JANUARY, 1);
        assertEquals("0100", format.format(cal));

        cal.set(999, Calendar.JANUARY, 1);
        assertEquals("0999", format.format(cal));

        cal.set(1000, Calendar.JANUARY, 1);
        assertEquals("1000", format.format(cal));

        cal.set(2023, Calendar.JANUARY, 1);
        assertEquals("2023", format.format(cal));
    }
}