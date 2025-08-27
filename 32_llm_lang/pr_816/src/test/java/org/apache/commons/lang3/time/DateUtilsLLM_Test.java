package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class DateUtilsLLM_Test {

    @Test
    public void testIteratorWithNullCalendar() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.iterator((Calendar) null, DateUtils.RANGE_WEEK_SUNDAY));
    }

    @Test
    public void testIteratorWithNullDate() {
        assertThrows(NullPointerException.class, () -> DateUtils.iterator((Date) null, DateUtils.RANGE_WEEK_SUNDAY));
    }

    @Test
    public void testIteratorWithNullObject() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.iterator((Object) null, DateUtils.RANGE_WEEK_SUNDAY));
    }

    @Test
    public void testIteratorWithInvalidRangeStyle() {
        Calendar cal = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> DateUtils.iterator(cal, -1));
    }

    @Test
    public void testIteratorWithValidRangeStyle() {
        Calendar cal = Calendar.getInstance();
        Iterator<Calendar> it = DateUtils.iterator(cal, DateUtils.RANGE_WEEK_SUNDAY);
        assertNotNull(it);
    }
}