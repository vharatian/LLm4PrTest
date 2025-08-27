package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;

import org.apache.commons.io.test.TestUtils;
import org.junit.jupiter.api.Test;

public class ThreadMonitorLLM_Test {

    @Test
    public void testGetNanosOfMilli() {
        Duration duration = Duration.ofMillis(1234).plusNanos(567890);
        int nanos = ThreadMonitor.getNanosOfMilli(duration);
        assertEquals(567890, nanos, "Nanoseconds part of the duration should be 567890");
    }

    @Test
    public void testSleepWithDuration() {
        try {
            Duration duration = Duration.ofMillis(100);
            long start = System.currentTimeMillis();
            ThreadMonitor.sleep(duration);
            long end = System.currentTimeMillis();
            long elapsed = end - start;
            assertEquals(100, elapsed, 10, "Sleep duration should be approximately 100 milliseconds");
        } catch (InterruptedException e) {
            fail("Sleep was interrupted", e);
        }
    }
}