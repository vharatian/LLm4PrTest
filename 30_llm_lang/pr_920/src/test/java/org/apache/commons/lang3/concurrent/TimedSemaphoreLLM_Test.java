package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class TimedSemaphoreLLM_Test {

    private static final long PERIOD = 500;
    private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;
    private static final int LIMIT = 10;

    @Test
    public void testShutdownDescription() {
        final TimedSemaphore semaphore = new TimedSemaphore(PERIOD, UNIT, LIMIT);
        semaphore.shutdown();
        assertThrows(IllegalStateException.class, semaphore::acquire);
    }

    @Test
    public void testAcquireDescription() throws InterruptedException {
        final TimedSemaphore semaphore = new TimedSemaphore(PERIOD, UNIT, LIMIT);
        semaphore.acquire();
        assertEquals(1, semaphore.getAcquireCount(), "Wrong acquire count");
    }
}