package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class ThresholdCircuitBreakerLLM_Test extends AbstractLangTest {
    private static final long threshold = 10L;
    private static final long zeroThreshold = 0L;

    @Test
    public void testCheckStateWhenClosed() {
        final ThresholdCircuitBreaker circuit = new ThresholdCircuitBreaker(threshold);
        circuit.incrementAndCheckState(9L);
        assertTrue(circuit.checkState(), "Circuit should be closed when below the threshold");
    }

    @Test
    public void testCheckStateWhenOpen() {
        final ThresholdCircuitBreaker circuit = new ThresholdCircuitBreaker(threshold);
        circuit.incrementAndCheckState(11L);
        assertFalse(circuit.checkState(), "Circuit should be open when above the threshold");
    }
}