package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Calendar;
import org.junit.jupiter.api.Test;

public class DurationFormatUtilsLLM_Test {

    @Test
    public void testFormatPeriodNoMsInFormatString() {
        final Calendar start = Calendar.getInstance();
        start.set(2023, Calendar.JANUARY, 1, 0, 0, 0);
        start.set(Calendar.MILLISECOND, 0);
        final long startMillis = start.getTimeInMillis();

        final Calendar end = Calendar.getInstance();
        end.set(2023, Calendar.FEBRUARY, 1, 0, 0, 0);
        end.set(Calendar.MILLISECOND, 0);
        final long endMillis = end.getTimeInMillis();

        String result = DurationFormatUtils.formatPeriod(startMillis, endMillis, "y M d H m s S");
        assertEquals("0 1 0 0 0 0 0", result);

        result = DurationFormatUtils.formatPeriod(startMillis, endMillis, "y M d");
        assertEquals("0 1 0", result);
    }

    @Test
    public void testFormatPeriodWithNegativeDuration() {
        final Calendar start = Calendar.getInstance();
        start.set(2023, Calendar.JANUARY, 1, 0, 0, 0);
        start.set(Calendar.MILLISECOND, 0);
        final long startMillis = start.getTimeInMillis();

        final Calendar end = Calendar.getInstance();
        end.set(2022, Calendar.JANUARY, 1, 0, 0, 0);
        end.set(Calendar.MILLISECOND, 0);
        final long endMillis = end.getTimeInMillis();

        assertThrows(IllegalArgumentException.class, () -> DurationFormatUtils.formatPeriod(startMillis, endMillis, "y M d H m s S"));
    }
}