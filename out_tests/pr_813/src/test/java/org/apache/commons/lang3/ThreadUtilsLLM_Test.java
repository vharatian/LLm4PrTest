package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;

import org.junit.jupiter.api.Test;

public class ThreadUtilsLLM_Test {

    @Test
    public void testSleepQuietly() {
        // Test that sleepQuietly does not throw an exception when interrupted
        assertDoesNotThrow(() -> ThreadUtils.sleepQuietly(1));

        // Test that sleepQuietly handles a large sleep duration without throwing an exception
        assertDoesNotThrow(() -> ThreadUtils.sleepQuietly(1000));
    }
}