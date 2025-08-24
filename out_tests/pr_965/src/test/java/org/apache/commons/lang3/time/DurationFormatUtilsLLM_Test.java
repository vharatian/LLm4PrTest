package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DurationFormatUtilsLLM_Test {

    @Test
    public void testFormatDurationWordsWithSuppressLeadingZeroElements() {
        // Test case to ensure the removal of "0 seconds" when suppressLeadingZeroElements is true
        String text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        assertEquals("1 minute 5 seconds", text);

        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        assertEquals("2 minutes 0 seconds", text);

        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        assertEquals("2 minutes 1 second", text);
    }

    @Test
    public void testFormatDurationWordsWithSuppressTrailingZeroElements() {
        // Test case to ensure the removal of "0 seconds" when suppressTrailingZeroElements is true
        String text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        assertEquals("0 days 0 hours 2 minutes", text);

        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, true);
        assertEquals("0 days 0 hours 2 minutes 1 second", text);
    }
}