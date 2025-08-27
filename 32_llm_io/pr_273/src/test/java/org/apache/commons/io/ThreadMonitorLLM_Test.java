package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

class ThreadMonitorLLM_Test {

    @Test
    void testSleepWithGuaranteedMinimumDuration() {
        Duration duration = Duration.ofMillis(100);
        Instant start = Instant.now();
        try {
            ThreadMonitor.sleep(duration);
        } catch (InterruptedException e) {
            fail("Sleep was interrupted");
        }
        Instant end = Instant.now();
        Duration elapsed = Duration.between(start, end);
        assertTrue(elapsed.compareTo(duration) >= 0, "Sleep did not last the minimum duration");
    }

    @Test
    void testGetNanosOfMiili() {
        Duration duration = Duration.ofMillis(100).plusNanos(500_000);
        int nanos = ThreadMonitor.getNanosOfMiili(duration);
        assertEquals(500_000, nanos, "Nanos of milli calculation is incorrect");
    }
}