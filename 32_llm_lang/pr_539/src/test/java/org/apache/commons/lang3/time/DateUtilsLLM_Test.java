package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.DefaultLocale;

public class DateUtilsLLM_Test {

    @Test
    public void testParseDateWithLeniency() throws Exception {
        final String dateStr = "02 942, 1996";
        final String[] parsers = new String[] {"MM DDD, yyyy"};
        final Date date = DateUtils.parseDate(dateStr, parsers);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        assertEquals(1996, cal.get(Calendar.YEAR));
        assertEquals(Calendar.JULY, cal.get(Calendar.MONTH));
        assertEquals(30, cal.get(Calendar.DAY_OF_MONTH));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly(dateStr, parsers));
    }

    @Test
    public void testParseDateStrictlyWithLocale() throws Exception {
        final String dateStr = "Mi, 09 Apr 2008 23:55:38 GMT";
        final String[] parsers = new String[] {"EEE, dd MMM yyyy HH:mm:ss zzz"};
        final Date date = DateUtils.parseDateStrictly(dateStr, Locale.GERMAN, parsers);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        assertEquals(2008, cal.get(Calendar.YEAR));
        assertEquals(Calendar.APRIL, cal.get(Calendar.MONTH));
        assertEquals(9, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(23, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(55, cal.get(Calendar.MINUTE));
        assertEquals(38, cal.get(Calendar.SECOND));
    }

    @Test
    public void testModifyWithSubtraction() {
        final Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.OCTOBER, 15);
        DateUtils.modify(cal, Calendar.DATE, DateUtils.ModifyType.TRUNCATE);
        assertEquals(1, cal.get(Calendar.DATE));
    }
}