package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;
import org.junit.jupiter.api.Test;

public class BackgroundInitializerLLM_Test {

    @Test
    public void testBuilderSetExternalExecutor() throws ConcurrentException {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        try {
            BackgroundInitializer<Integer> initializer = BackgroundInitializer.<Integer>builder()
                .setExternalExecutor(exec)
                .get();
            assertNotNull(initializer);
            assertEquals(exec, initializer.getExternalExecutor());
        } finally {
            exec.shutdown();
        }
    }

    @Test
    public void testBuilderWithoutExternalExecutor() throws ConcurrentException {
        BackgroundInitializer<Integer> initializer = BackgroundInitializer.<Integer>builder()
            .get();
        assertNotNull(initializer);
        assertEquals(null, initializer.getExternalExecutor());
    }

    @Test
    public void testBuilderWithInitializerAndCloser() throws ConcurrentException {
        FailableSupplier<Integer, ConcurrentException> initializerSupplier = () -> 42;
        FailableConsumer<Integer, ConcurrentException> closerConsumer = (value) -> {};

        BackgroundInitializer<Integer> initializer = new BackgroundInitializer.Builder<BackgroundInitializer<Integer>, Integer>()
            .setInitializer(initializerSupplier)
            .setCloser(closerConsumer)
            .get();

        assertNotNull(initializer);
        assertEquals(42, initializer.get().intValue());
    }

    @Test
    public void testGetTypedException() {
        BackgroundInitializer<Integer> initializer = new BackgroundInitializer<>();
        Exception originalException = new Exception("Test Exception");
        Exception typedException = initializer.getTypedException(originalException);
        assertNotNull(typedException);
        assertEquals(Exception.class, typedException.getClass());
        assertEquals(originalException, typedException.getCause());
    }
}