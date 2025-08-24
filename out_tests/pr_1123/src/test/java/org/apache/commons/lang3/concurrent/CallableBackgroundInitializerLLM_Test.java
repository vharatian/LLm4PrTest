package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class CallableBackgroundInitializerLLM_Test extends AbstractLangTest {

    @Test
    public void testGetTypedException() {
        Exception originalException = new Exception("Original Exception");
        CallableBackgroundInitializer<Integer> init = new CallableBackgroundInitializer<>(new TestCallable());
        Exception typedException = init.getTypedException(originalException);
        assertEquals(originalException, typedException.getCause(), "Cause of the exception does not match");
    }

    private static final class TestCallable implements Callable<Integer> {
        int callCount;
        @Override
        public Integer call() {
            callCount++;
            return Integer.valueOf(42);
        }
    }
}