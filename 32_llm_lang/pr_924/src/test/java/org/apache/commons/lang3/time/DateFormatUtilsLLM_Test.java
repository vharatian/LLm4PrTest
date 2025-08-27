package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;

public class DateFormatUtilsLLM_Test {

    /**
     * Test to ensure that using 'YYYY' instead of 'yyyy' in the pattern
     * defaults to the current year as week year is not supported.
     */
    @Test
    public void testWeekYearPattern() {
        final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.clear();
        cal.set(2023, Calendar.JANUARY, 1, 0, 0, 0); // Set a specific date

        // Using 'YYYY' should default to the current year
        String formattedDate = DateFormatUtils.format(cal.getTime(), "YYYY-MM-dd");
        assertEquals("2023-01-01", formattedDate);

        // Using 'yyyy' should also return the same result for this specific date
        formattedDate = DateFormatUtils.format(cal.getTime(), "yyyy-MM-dd");
        assertEquals("2023-01-01", formattedDate);
    }
}