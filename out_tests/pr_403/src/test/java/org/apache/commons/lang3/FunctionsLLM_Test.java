package org.apache.commons.lang3;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.UndeclaredThrowableException;
import org.apache.commons.lang3.Functions.FailableConsumer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class FunctionsLLM_Test {

    // Test for FailableRunnable interface
    @Test
    void testFailableRunnable() {
        try {
            Functions.run(() -> {
                throw new SomeException("Test Exception");
            });
            fail("Expected Exception");
        } catch (UndeclaredThrowableException e) {
            final Throwable cause = e.getCause();
            assertNotNull(cause);
            assertTrue(cause instanceof SomeException);
            assertEquals("Test Exception", cause.getMessage());
        }
    }

    // Test for FailableCallable interface
    @Test
    void testFailableCallable() {
        try {
            Functions.call(() -> {
                throw new SomeException("Test Exception");
            });
            fail("Expected Exception");
        } catch (UndeclaredThrowableException e) {
            final Throwable cause = e.getCause();
            assertNotNull(cause);
            assertTrue(cause instanceof SomeException);
            assertEquals("Test Exception", cause.getMessage());
        }
    }

    // Test for FailableConsumer interface
    @Test
    void testFailableConsumer() {
        final IllegalStateException ise = new IllegalStateException();
        try {
            Functions.accept((o) -> {
                throw ise;
            }, new Object());
            fail("Expected Exception");
        } catch (IllegalStateException e) {
            assertSame(ise, e);
        }
    }

    // Test for FailableBiConsumer interface
    @Test
    void testFailableBiConsumer() {
        final IllegalStateException ise = new IllegalStateException();
        try {
            Functions.accept((o1, o2) -> {
                throw ise;
            }, new Object(), new Object());
            fail("Expected Exception");
        } catch (IllegalStateException e) {
            assertSame(ise, e);
        }
    }

    // Test for FailableFunction interface
    @Test
    void testFailableFunction() {
        final IllegalStateException ise = new IllegalStateException();
        try {
            Functions.apply((o) -> {
                throw ise;
            }, new Object());
            fail("Expected Exception");
        } catch (IllegalStateException e) {
            assertSame(ise, e);
        }
    }

    // Test for FailableBiFunction interface
    @Test
    void testFailableBiFunction() {
        final IllegalStateException ise = new IllegalStateException();
        try {
            Functions.apply((o1, o2) -> {
                throw ise;
            }, new Object(), new Object());
            fail("Expected Exception");
        } catch (IllegalStateException e) {
            assertSame(ise, e);
        }
    }

    // Test for FailablePredicate interface
    @Test
    void testFailablePredicate() {
        final IllegalStateException ise = new IllegalStateException();
        try {
            Functions.test((o) -> {
                throw ise;
            }, new Object());
            fail("Expected Exception");
        } catch (IllegalStateException e) {
            assertSame(ise, e);
        }
    }

    // Test for FailableBiPredicate interface
    @Test
    void testFailableBiPredicate() {
        final IllegalStateException ise = new IllegalStateException();
        try {
            Functions.test((o1, o2) -> {
                throw ise;
            }, new Object(), new Object());
            fail("Expected Exception");
        } catch (IllegalStateException e) {
            assertSame(ise, e);
        }
    }

    // Test for tryWithResources method with error handler
    @Test
    void testTryWithResourcesWithErrorHandler() {
        final CloseableObject co = new CloseableObject();
        final FailableConsumer<Throwable, ? extends Throwable> consumer = co::run;
        final IllegalStateException ise = new IllegalStateException();
        try {
            Functions.tryWithResources(() -> consumer.accept(ise), (t) -> {
                throw new SomeException("Error Handler Exception");
            }, co::close);
            fail("Expected Exception");
        } catch (SomeException e) {
            assertEquals("Error Handler Exception", e.getMessage());
        }
        assertTrue(co.isClosed());
    }
}