package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;
import org.junit.jupiter.api.Test;

public abstract class AbstractConcurrentInitializerLLM_Test extends AbstractLangTest {

    @Test
    public void testClose() throws Exception {
        AbstractConcurrentInitializer<Object, Exception> initializer = createInitializer();
        FailableConsumer<Object, Exception> closer = mock(FailableConsumer.class);
        FailableSupplier<Object, Exception> supplier = mock(FailableSupplier.class);
        when(supplier.get()).thenReturn(new Object());

        initializer = new AbstractConcurrentInitializer<>(supplier, closer) {
            @Override
            protected boolean isInitialized() {
                return true;
            }

            @Override
            protected Exception getTypedException(Exception e) {
                return e;
            }
        };

        assertDoesNotThrow(() -> initializer.close(), "Close should not throw an exception");
    }

    @Test
    public void testCloseWithException() throws Exception {
        AbstractConcurrentInitializer<Object, Exception> initializer = createInitializer();
        FailableConsumer<Object, Exception> closer = mock(FailableConsumer.class);
        FailableSupplier<Object, Exception> supplier = mock(FailableSupplier.class);
        when(supplier.get()).thenReturn(new Object());
        when(closer.accept(new Object())).thenThrow(new Exception("Test Exception"));

        initializer = new AbstractConcurrentInitializer<>(supplier, closer) {
            @Override
            protected boolean isInitialized() {
                return true;
            }

            @Override
            protected Exception getTypedException(Exception e) {
                return e;
            }
        };

        assertThrows(ConcurrentException.class, () -> initializer.close(), "Close should throw ConcurrentException");
    }

    @Test
    public void testInitializeWithException() throws Exception {
        AbstractConcurrentInitializer<Object, Exception> initializer = createInitializer();
        FailableSupplier<Object, Exception> supplier = mock(FailableSupplier.class);
        when(supplier.get()).thenThrow(new Exception("Test Exception"));

        initializer = new AbstractConcurrentInitializer<>(supplier, FailableConsumer.nop()) {
            @Override
            protected boolean isInitialized() {
                return false;
            }

            @Override
            protected Exception getTypedException(Exception e) {
                return e;
            }
        };

        assertThrows(Exception.class, () -> initializer.get(), "Initialize should throw Exception");
    }

    @Test
    public void testInitialize() throws Exception {
        AbstractConcurrentInitializer<Object, Exception> initializer = createInitializer();
        FailableSupplier<Object, Exception> supplier = mock(FailableSupplier.class);
        Object expectedObject = new Object();
        when(supplier.get()).thenReturn(expectedObject);

        initializer = new AbstractConcurrentInitializer<>(supplier, FailableConsumer.nop()) {
            @Override
            protected boolean isInitialized() {
                return false;
            }

            @Override
            protected Exception getTypedException(Exception e) {
                return e;
            }
        };

        assertTrue(initializer.get() == expectedObject, "Initialize should return the expected object");
    }

    protected abstract AbstractConcurrentInitializer<Object, Exception> createInitializer();
}