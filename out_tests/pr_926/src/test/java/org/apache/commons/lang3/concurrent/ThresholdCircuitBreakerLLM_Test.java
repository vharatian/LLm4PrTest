package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class ThresholdCircuitBreakerLLM_Test extends AbstractLangTest {
    private static final long threshold = 10L;
    private static final long zeroThreshold = 0L;

    @Test
    public void testThreshold() {
        final ThresholdCircuitBreaker circuit = new ThresholdCircuitBreaker(threshold);
        circuit.incrementAndCheckState(9L);
        assertFalse(circuit.incrementAndCheckState(1L), "Circuit opened before reaching the threshold");
    }

    @Test
    public void testThresholdCircuitBreakingException() {
        final ThresholdCircuitBreaker circuit = new ThresholdCircuitBreaker(threshold);
        circuit.incrementAndCheckState(9L);
        assertTrue(circuit.incrementAndCheckState(2L), "The circuit was supposed to be open after increment above the threshold");
    }

    @Test
    public void testThresholdEqualsZero() {
        final ThresholdCircuitBreaker circuit = new ThresholdCircuitBreaker(zeroThreshold);
        assertTrue(circuit.incrementAndCheckState(0L), "When the threshold is zero, the circuit is supposed to be always open");
    }

    @Test
    public void testClosingThresholdCircuitBreaker() {
        final ThresholdCircuitBreaker circuit = new ThresholdCircuitBreaker(threshold);
        circuit.incrementAndCheckState(9L);
        circuit.close();
        assertFalse(circuit.incrementAndCheckState(9L), "Internal counter was not reset back to zero");
    }

    @Test
    public void testGettingThreshold() {
        final ThresholdCircuitBreaker circuit = new ThresholdCircuitBreaker(threshold);
        assertEquals(Long.valueOf(threshold), Long.valueOf(circuit.getThreshold()), "Wrong value of threshold");
    }
}