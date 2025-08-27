package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class DateUtilsLLM_Test {

    @Test
    public void testGetFragmentInMilliseconds() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Test for Date
        long fragmentInMilliseconds = DateUtils.getFragmentInMilliseconds(date, Calendar.DAY_OF_YEAR);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000 +
                     calendar.get(Calendar.MINUTE) * 60 * 1000 +
                     calendar.get(Calendar.SECOND) * 1000 +
                     calendar.get(Calendar.MILLISECOND), fragmentInMilliseconds);

        // Test for Calendar
        fragmentInMilliseconds = DateUtils.getFragmentInMilliseconds(calendar, Calendar.DAY_OF_YEAR);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000 +
                     calendar.get(Calendar.MINUTE) * 60 * 1000 +
                     calendar.get(Calendar.SECOND) * 1000 +
                     calendar.get(Calendar.MILLISECOND), fragmentInMilliseconds);
    }

    @Test
    public void testGetFragmentInSeconds() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Test for Date
        long fragmentInSeconds = DateUtils.getFragmentInSeconds(date, Calendar.DAY_OF_YEAR);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 +
                     calendar.get(Calendar.MINUTE) * 60 +
                     calendar.get(Calendar.SECOND), fragmentInSeconds);

        // Test for Calendar
        fragmentInSeconds = DateUtils.getFragmentInSeconds(calendar, Calendar.DAY_OF_YEAR);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 +
                     calendar.get(Calendar.MINUTE) * 60 +
                     calendar.get(Calendar.SECOND), fragmentInSeconds);
    }

    @Test
    public void testGetFragmentInMinutes() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Test for Date
        long fragmentInMinutes = DateUtils.getFragmentInMinutes(date, Calendar.DAY_OF_YEAR);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY) * 60 +
                     calendar.get(Calendar.MINUTE), fragmentInMinutes);

        // Test for Calendar
        fragmentInMinutes = DateUtils.getFragmentInMinutes(calendar, Calendar.DAY_OF_YEAR);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY) * 60 +
                     calendar.get(Calendar.MINUTE), fragmentInMinutes);
    }

    @Test
    public void testGetFragmentInHours() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Test for Date
        long fragmentInHours = DateUtils.getFragmentInHours(date, Calendar.DAY_OF_YEAR);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY), fragmentInHours);

        // Test for Calendar
        fragmentInHours = DateUtils.getFragmentInHours(calendar, Calendar.DAY_OF_YEAR);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY), fragmentInHours);
    }

    @Test
    public void testGetFragmentInDays() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Test for Date
        long fragmentInDays = DateUtils.getFragmentInDays(date, Calendar.YEAR);
        assertEquals(calendar.get(Calendar.DAY_OF_YEAR) - 1, fragmentInDays);

        // Test for Calendar
        fragmentInDays = DateUtils.getFragmentInDays(calendar, Calendar.YEAR);
        assertEquals(calendar.get(Calendar.DAY_OF_YEAR) - 1, fragmentInDays);
    }

    @Test
    public void testGetFragmentInvalidFragment() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Test for Date
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInMilliseconds(date, Calendar.ERA));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInSeconds(date, Calendar.ERA));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInMinutes(date, Calendar.ERA));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInHours(date, Calendar.ERA));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInDays(date, Calendar.ERA));

        // Test for Calendar
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInMilliseconds(calendar, Calendar.ERA));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInSeconds(calendar, Calendar.ERA));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInMinutes(calendar, Calendar.ERA));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInHours(calendar, Calendar.ERA));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInDays(calendar, Calendar.ERA));
    }
}