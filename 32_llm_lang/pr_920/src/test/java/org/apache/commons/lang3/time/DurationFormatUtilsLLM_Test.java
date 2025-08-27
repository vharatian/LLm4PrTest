package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class DurationFormatUtilsLLM_Test {

    @Test
    public void testFormatDurationWithPadding() {
        // Test with padding
        assertEquals("0001", DurationFormatUtils.formatDuration(1, "S", true));
        assertEquals("00001", DurationFormatUtils.formatDuration(1, "SS", true));
        assertEquals("000001", DurationFormatUtils.formatDuration(1, "SSS", true));
        assertEquals("0000001", DurationFormatUtils.formatDuration(1, "SSSS", true));
    }

    @Test
    public void testFormatDurationWithoutPadding() {
        // Test without padding
        assertEquals("1", DurationFormatUtils.formatDuration(1, "S", false));
        assertEquals("1", DurationFormatUtils.formatDuration(1, "SS", false));
        assertEquals("1", DurationFormatUtils.formatDuration(1, "SSS", false));
        assertEquals("1", DurationFormatUtils.formatDuration(1, "SSSS", false));
    }

    @Test
    public void testFormatPeriodWithPadding() {
        final long startMillis = 0L;
        final long endMillis = 1000L;

        // Test with padding
        assertEquals("0000", DurationFormatUtils.formatPeriod(startMillis, endMillis, "S", true));
        assertEquals("00001", DurationFormatUtils.formatPeriod(startMillis, endMillis, "SS", true));
        assertEquals("000001", DurationFormatUtils.formatPeriod(startMillis, endMillis, "SSS", true));
        assertEquals("0000001", DurationFormatUtils.formatPeriod(startMillis, endMillis, "SSSS", true));
    }

    @Test
    public void testFormatPeriodWithoutPadding() {
        final long startMillis = 0L;
        final long endMillis = 1000L;

        // Test without padding
        assertEquals("1", DurationFormatUtils.formatPeriod(startMillis, endMillis, "S", false));
        assertEquals("1", DurationFormatUtils.formatPeriod(startMillis, endMillis, "SS", false));
        assertEquals("1", DurationFormatUtils.formatPeriod(startMillis, endMillis, "SSS", false));
        assertEquals("1", DurationFormatUtils.formatPeriod(startMillis, endMillis, "SSSS", false));
    }
}