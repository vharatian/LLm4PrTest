package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

public class DateUtilsLLM_Test {

    @Test
    public void testIterator_NullFocus() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.iterator((Calendar) null, DateUtils.RANGE_WEEK_SUNDAY));
    }
}