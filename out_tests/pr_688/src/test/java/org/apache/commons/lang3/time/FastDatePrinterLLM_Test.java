package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import org.junit.jupiter.api.Test;

public class FastDatePrinterLLM_Test {

    private DatePrinter getInstance(final String format, final TimeZone timeZone, final Locale locale) {
        return new FastDatePrinter(format, timeZone, locale);
    }

    @Test
    public void testTwoDigitYearField() {
        final Calendar cal = Calendar.getInstance();
        final DatePrinter printer = getInstance("yy", TimeZone.getDefault(), Locale.getDefault());

        // Test year 1999
        cal.set(Calendar.YEAR, 1999);
        assertEquals("99", printer.format(cal));

        // Test year 2000
        cal.set(Calendar.YEAR, 2000);
        assertEquals("00", printer.format(cal));

        // Test year 2023
        cal.set(Calendar.YEAR, 2023);
        assertEquals("23", printer.format(cal));

        // Test year 2100
        cal.set(Calendar.YEAR, 2100);
        assertEquals("00", printer.format(cal));
    }
}