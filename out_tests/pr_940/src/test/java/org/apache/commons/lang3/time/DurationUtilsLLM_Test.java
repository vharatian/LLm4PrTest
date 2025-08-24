package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class DurationUtilsLLM_Test extends AbstractLangTest {

    @Test
    public void testGetNanosOfMilli() {
        assertEquals(0, DurationUtils.getNanosOfMilli(null));
        assertEquals(0, DurationUtils.getNanosOfMilli(Duration.ZERO));
        assertEquals(1, DurationUtils.getNanosOfMilli(Duration.ofNanos(1)));
        assertEquals(10, DurationUtils.getNanosOfMilli(Duration.ofNanos(10)));
        assertEquals(100, DurationUtils.getNanosOfMilli(Duration.ofNanos(100)));
        assertEquals(1_000, DurationUtils.getNanosOfMilli(Duration.ofNanos(1_000)));
        assertEquals(10_000, DurationUtils.getNanosOfMilli(Duration.ofNanos(10_000)));
        assertEquals(100_000, DurationUtils.getNanosOfMilli(Duration.ofNanos(100_000)));
        assertEquals(0, DurationUtils.getNanosOfMilli(Duration.ofNanos(1_000_000)));
        assertEquals(1, DurationUtils.getNanosOfMilli(Duration.ofNanos(1_000_001)));
    }

    @Test
    public void testDeprecatedGetNanosOfMiili() {
        assertEquals(0, DurationUtils.getNanosOfMiili(null));
        assertEquals(0, DurationUtils.getNanosOfMiili(Duration.ZERO));
        assertEquals(1, DurationUtils.getNanosOfMiili(Duration.ofNanos(1)));
        assertEquals(10, DurationUtils.getNanosOfMiili(Duration.ofNanos(10)));
        assertEquals(100, DurationUtils.getNanosOfMiili(Duration.ofNanos(100)));
        assertEquals(1_000, DurationUtils.getNanosOfMiili(Duration.ofNanos(1_000)));
        assertEquals(10_000, DurationUtils.getNanosOfMiili(Duration.ofNanos(10_000)));
        assertEquals(100_000, DurationUtils.getNanosOfMiili(Duration.ofNanos(100_000)));
        assertEquals(0, DurationUtils.getNanosOfMiili(Duration.ofNanos(1_000_000)));
        assertEquals(1, DurationUtils.getNanosOfMiili(Duration.ofNanos(1_000_001)));
    }
}