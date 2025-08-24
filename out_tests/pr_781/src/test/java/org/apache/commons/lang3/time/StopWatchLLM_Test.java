package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.ThreadUtils;
import org.junit.jupiter.api.Test;

public class StopWatchLLM_Test {

    private static final int MIN_SLEEP_MILLISECONDS = 20;
    private static final String ZERO_HOURS_PREFIX = "00:";
    private static final String ZERO_TIME_ELAPSED = "00:00:00.000";
    private static final String MESSAGE = "Baking cookies";

    private void sleepQuietly(final Duration duration) throws InterruptedException {
        ThreadUtils.sleep(duration);
    }

    @Test
    public void testFormatSplitTime() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        ThreadUtils.sleepQuietly(MIN_SLEEP_MILLISECONDS);
        watch.split();
        final String formatSplitTime = watch.formatSplitTime();
        assertNotEquals(ZERO_TIME_ELAPSED, formatSplitTime);
        assertTrue(formatSplitTime.startsWith(ZERO_HOURS_PREFIX));
    }

    @Test
    public void testFormatTime() {
        final StopWatch watch = StopWatch.create();
        final String formatTime = watch.formatTime();
        assertEquals(ZERO_TIME_ELAPSED, formatTime);
        assertTrue(formatTime.startsWith(ZERO_HOURS_PREFIX));
    }

    @Test
    public void testFormatSplitTimeWithMessage() throws InterruptedException {
        final StopWatch watch = new StopWatch(MESSAGE);
        watch.start();
        ThreadUtils.sleepQuietly(MIN_SLEEP_MILLISECONDS);
        watch.split();
        final String formatSplitTime = watch.formatSplitTime();
        assertFalse(formatSplitTime.startsWith(MESSAGE), formatSplitTime);
        assertTrue(formatSplitTime.startsWith(ZERO_HOURS_PREFIX));
    }

    @Test
    public void testFormatTimeWithMessage() {
        final StopWatch watch = new StopWatch(MESSAGE);
        final String formatTime = watch.formatTime();
        assertFalse(formatTime.startsWith(MESSAGE), formatTime);
    }
}