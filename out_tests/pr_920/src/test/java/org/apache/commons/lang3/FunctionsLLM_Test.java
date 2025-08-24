package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.UndeclaredThrowableException;

import org.junit.jupiter.api.Test;

public class FunctionsLLM_Test {

    /**
     * Test for the corrected typo in the javadoc of the rethrow method.
     */
    @Test
    public void testRethrowJavadocCorrection() {
        final IOException ioException = new IOException("test");
        final RuntimeException runtimeException = assertThrows(UncheckedIOException.class, () -> {
            throw Functions.rethrow(ioException);
        });
        assertSame(ioException, runtimeException.getCause());
    }

    /**
     * Test for the corrected typo in the javadoc of the tryWithResources method.
     */
    @Test
    public void testTryWithResourcesJavadocCorrection() {
        final FunctionsTest.CloseableObject co = new FunctionsTest.CloseableObject();
        final Functions.FailableConsumer<Throwable, ? extends Throwable> consumer = co::run;
        final IllegalStateException ise = new IllegalStateException();
        Throwable e = assertThrows(IllegalStateException.class,
                () -> Functions.tryWithResources(() -> consumer.accept(ise), co::close));
        assertSame(ise, e);
        assertTrue(co.isClosed());
        co.reset();
        final Error error = new OutOfMemoryError();
        e = assertThrows(OutOfMemoryError.class,
                () -> Functions.tryWithResources(() -> consumer.accept(error), co::close));
        assertSame(error, e);
        assertTrue(co.isClosed());
        co.reset();
        final IOException ioe = new IOException("Unknown I/O error");
        final UncheckedIOException uioe = assertThrows(UncheckedIOException.class,
                () -> Functions.tryWithResources(() -> consumer.accept(ioe), co::close));
        final IOException cause = uioe.getCause();
        assertSame(ioe, cause);
        assertTrue(co.isClosed());
        co.reset();
        Functions.tryWithResources(() -> consumer.accept(null), co::close);
        assertTrue(co.isClosed());
    }
}