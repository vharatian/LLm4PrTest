package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class DateUtilsLLM_Test {

    @Test
    public void testAddNullDate() {
        assertThrows(NullPointerException.class, () -> DateUtils.add(null, Calendar.YEAR, 1));
    }

    @Test
    public void testRoundNullCalendar() {
        assertThrows(NullPointerException.class, () -> DateUtils.round((Calendar) null, Calendar.YEAR));
    }

    @Test
    public void testRoundNullObject() {
        assertThrows(NullPointerException.class, () -> DateUtils.round((Object) null, Calendar.YEAR));
    }

    @Test
    public void testTruncateNullDate() {
        assertThrows(NullPointerException.class, () -> DateUtils.truncate((Date) null, Calendar.YEAR));
    }

    @Test
    public void testTruncateNullCalendar() {
        assertThrows(NullPointerException.class, () -> DateUtils.truncate((Calendar) null, Calendar.YEAR));
    }

    @Test
    public void testTruncateNullObject() {
        assertThrows(NullPointerException.class, () -> DateUtils.truncate((Object) null, Calendar.YEAR));
    }

    @Test
    public void testCeilingNullDate() {
        assertThrows(NullPointerException.class, () -> DateUtils.ceiling((Date) null, Calendar.YEAR));
    }

    @Test
    public void testCeilingNullCalendar() {
        assertThrows(NullPointerException.class, () -> DateUtils.ceiling((Calendar) null, Calendar.YEAR));
    }

    @Test
    public void testCeilingNullObject() {
        assertThrows(NullPointerException.class, () -> DateUtils.ceiling((Object) null, Calendar.YEAR));
    }

    @Test
    public void testIteratorNullDate() {
        assertThrows(NullPointerException.class, () -> DateUtils.iterator((Date) null, DateUtils.RANGE_WEEK_CENTER));
    }

    @Test
    public void testGetFragmentNullDate() {
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInMilliseconds((Date) null, Calendar.YEAR));
    }

    @Test
    public void testGetFragmentNullCalendar() {
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInMilliseconds((Calendar) null, Calendar.YEAR));
    }
}