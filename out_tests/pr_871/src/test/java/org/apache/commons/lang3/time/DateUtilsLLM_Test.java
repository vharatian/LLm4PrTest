package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Date;
import java.util.Calendar;
import org.junit.jupiter.api.Test;

public class DateUtilsLLM_Test {

    @Test
    public void testAddYears_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.addYears(null, 1));
    }

    @Test
    public void testAddMonths_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.addMonths(null, 1));
    }

    @Test
    public void testAddWeeks_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.addWeeks(null, 1));
    }

    @Test
    public void testAddDays_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.addDays(null, 1));
    }

    @Test
    public void testAddHours_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.addHours(null, 1));
    }

    @Test
    public void testAddMinutes_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.addMinutes(null, 1));
    }

    @Test
    public void testAddSeconds_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.addSeconds(null, 1));
    }

    @Test
    public void testAddMilliseconds_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.addMilliseconds(null, 1));
    }

    @Test
    public void testSetYears_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.setYears(null, 2000));
    }

    @Test
    public void testSetMonths_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.setMonths(null, 1));
    }

    @Test
    public void testSetMonths_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.setMonths(new Date(), 12));
    }

    @Test
    public void testSetDays_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.setDays(null, 1));
    }

    @Test
    public void testSetDays_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.setDays(new Date(), 32));
    }

    @Test
    public void testSetHours_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.setHours(null, 1));
    }

    @Test
    public void testSetHours_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.setHours(new Date(), 24));
    }

    @Test
    public void testSetMinutes_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.setMinutes(null, 1));
    }

    @Test
    public void testSetMinutes_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.setMinutes(new Date(), 60));
    }

    @Test
    public void testSetSeconds_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.setSeconds(null, 1));
    }

    @Test
    public void testSetSeconds_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.setSeconds(new Date(), 60));
    }

    @Test
    public void testSetMilliseconds_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.setMilliseconds(null, 1));
    }

    @Test
    public void testSetMilliseconds_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.setMilliseconds(new Date(), 1000));
    }

    @Test
    public void testRound_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.round((Date) null, Calendar.SECOND));
    }

    @Test
    public void testTruncate_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.truncate((Date) null, Calendar.SECOND));
    }

    @Test
    public void testCeiling_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.ceiling((Date) null, Calendar.SECOND));
    }

    @Test
    public void testIterator_Date_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.iterator((Date) null, DateUtils.RANGE_WEEK_CENTER));
    }

    @Test
    public void testIterator_Calendar_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.iterator((Calendar) null, DateUtils.RANGE_WEEK_CENTER));
    }

    @Test
    public void testGetFragmentInMilliseconds_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInMilliseconds((Date) null, Calendar.SECOND));
    }

    @Test
    public void testGetFragmentInSeconds_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInSeconds((Date) null, Calendar.SECOND));
    }

    @Test
    public void testGetFragmentInMinutes_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInMinutes((Date) null, Calendar.SECOND));
    }

    @Test
    public void testGetFragmentInHours_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInHours((Date) null, Calendar.SECOND));
    }

    @Test
    public void testGetFragmentInDays_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInDays((Date) null, Calendar.SECOND));
    }

    @Test
    public void testTruncatedEquals_Date_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.truncatedEquals((Date) null, new Date(), Calendar.SECOND));
    }

    @Test
    public void testTruncatedEquals_Calendar_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.truncatedEquals((Calendar) null, Calendar.getInstance(), Calendar.SECOND));
    }

    @Test
    public void testTruncatedCompareTo_Date_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.truncatedCompareTo((Date) null, new Date(), Calendar.SECOND));
    }

    @Test
    public void testTruncatedCompareTo_Calendar_NullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.truncatedCompareTo((Calendar) null, Calendar.getInstance(), Calendar.SECOND));
    }
}