package org.apache.commons.lang3;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.lang3.Functions.FailableBiConsumer;
import org.apache.commons.lang3.Functions.FailableBiFunction;
import org.apache.commons.lang3.Functions.FailableCallable;
import org.apache.commons.lang3.Functions.FailableConsumer;
import org.apache.commons.lang3.Functions.FailableFunction;
import org.apache.commons.lang3.Functions.FailableSupplier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionsLLM_Test {

    @Test
    void testAsRunnableWithRun() {
        FailureOnOddInvocations.invocation = 0;
        Runnable runnable = Functions.asRunnable(() -> new FailureOnOddInvocations());
        UndeclaredThrowableException e = assertThrows(UndeclaredThrowableException.class, runnable::run);
        final Throwable cause = e.getCause();
        assertNotNull(cause);
        assertTrue(cause instanceof SomeException);
        assertEquals("Odd Invocation: 1", cause.getMessage());
        runnable.run();
    }

    @Test
    void testAsConsumerWithAccept() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable testable = new Testable(ise);
        final Consumer<Testable> consumer = Functions.asConsumer(Testable::test);
        Throwable e = assertThrows(IllegalStateException.class, () -> consumer.accept(testable));
        assertSame(ise, e);
        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        e = assertThrows(OutOfMemoryError.class, () -> consumer.accept(testable));
        assertSame(error, e);
        final IOException ioe = new IOException("Unknown I/O error");
        testable.setThrowable(ioe);
        e = assertThrows(UncheckedIOException.class, () -> consumer.accept(testable));
        final Throwable t = e.getCause();
        assertNotNull(t);
        assertSame(ioe, t);
        testable.setThrowable(null);
        consumer.accept(testable);
    }

    @Test
    void testAsCallableWithCall() {
        FailureOnOddInvocations.invocation = 0;
        final FailableCallable<FailureOnOddInvocations, SomeException> failableCallable = FailureOnOddInvocations::new;
        final Callable<FailureOnOddInvocations> callable = Functions.asCallable(failableCallable);
        UndeclaredThrowableException e = assertThrows(UndeclaredThrowableException.class, callable::call);
        final Throwable cause = e.getCause();
        assertNotNull(cause);
        assertTrue(cause instanceof SomeException);
        assertEquals("Odd Invocation: 1", cause.getMessage());
        final FailureOnOddInvocations instance;
        try {
            instance = callable.call();
        } catch (Exception ex) {
            throw Functions.rethrow(ex);
        }
        assertNotNull(instance);
    }

    @Test
    void testAsBiConsumerWithAccept() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable testable = new Testable(null);
        final FailableBiConsumer<Testable, Throwable, Throwable> failableBiConsumer = (t, th) -> {
            t.setThrowable(th);
            t.test();
        };
        final BiConsumer<Testable, Throwable> consumer = Functions.asBiConsumer(failableBiConsumer);
        Throwable e = assertThrows(IllegalStateException.class, () -> consumer.accept(testable, ise));
        assertSame(ise, e);
        final Error error = new OutOfMemoryError();
        e = assertThrows(OutOfMemoryError.class, () -> consumer.accept(testable, error));
        assertSame(error, e);
        final IOException ioe = new IOException("Unknown I/O error");
        testable.setThrowable(ioe);
        e = assertThrows(UncheckedIOException.class, () -> consumer.accept(testable, ioe));
        final Throwable t = e.getCause();
        assertNotNull(t);
        assertSame(ioe, t);
        consumer.accept(testable, null);
    }

    @Test
    void testAsFunctionWithApply() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable testable = new Testable(ise);
        final FailableFunction<Throwable, Integer, Throwable> failableFunction = (th) -> {
            testable.setThrowable(th);
            return testable.testInt();
        };
        final Function<Throwable, Integer> function = Functions.asFunction(failableFunction);
        Throwable e = assertThrows(IllegalStateException.class, () -> function.apply(ise));
        assertSame(ise, e);
        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        e = assertThrows(OutOfMemoryError.class, () -> function.apply(error));
        assertSame(error, e);
        final IOException ioe = new IOException("Unknown I/O error");
        testable.setThrowable(ioe);
        e = assertThrows(UncheckedIOException.class, () -> function.apply(ioe));
        final Throwable t = e.getCause();
        assertNotNull(t);
        assertSame(ioe, t);
        assertEquals(0, function.apply(null).intValue());
    }

    @Test
    void testAsBiFunctionWithApply() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable testable = new Testable(ise);
        final FailableBiFunction<Testable, Throwable, Integer, Throwable> failableBiFunction = (t, th) -> {
            t.setThrowable(th);
            return t.testInt();
        };
        final BiFunction<Testable, Throwable, Integer> biFunction = Functions.asBiFunction(failableBiFunction);
        Throwable e = assertThrows(IllegalStateException.class, () -> biFunction.apply(testable, ise));
        assertSame(ise, e);
        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        e = assertThrows(OutOfMemoryError.class, () -> biFunction.apply(testable, error));
        assertSame(error, e);
        final IOException ioe = new IOException("Unknown I/O error");
        testable.setThrowable(ioe);
        e = assertThrows(UncheckedIOException.class, () -> biFunction.apply(testable, ioe));
        final Throwable t = e.getCause();
        assertNotNull(t);
        assertSame(ioe, t);
        assertEquals(0, biFunction.apply(testable, null).intValue());
    }

    @Test
    void testAsPredicateWithTest() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable testable = new Testable(ise);
        final FailablePredicate<Testable, Throwable> failablePredicate = (t) -> {
            t.test();
            return true;
        };
        final Predicate<Testable> predicate = Functions.asPredicate(failablePredicate);
        Throwable e = assertThrows(IllegalStateException.class, () -> predicate.test(testable));
        assertSame(ise, e);
        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        e = assertThrows(OutOfMemoryError.class, () -> predicate.test(testable));
        assertSame(error, e);
        final IOException ioe = new IOException("Unknown I/O error");
        testable.setThrowable(ioe);
        e = assertThrows(UncheckedIOException.class, () -> predicate.test(testable));
        final Throwable t = e.getCause();
        assertNotNull(t);
        assertSame(ioe, t);
        testable.setThrowable(null);
        assertTrue(predicate.test(testable));
    }

    @Test
    void testAsBiPredicateWithTest() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable testable = new Testable(null);
        final FailableBiPredicate<Testable, Throwable, Throwable> failableBiPredicate = (t, th) -> {
            t.setThrowable(th);
            t.test();
            return true;
        };
        final BiPredicate<Testable, Throwable> predicate = Functions.asBiPredicate(failableBiPredicate);
        Throwable e = assertThrows(IllegalStateException.class, () -> predicate.test(testable, ise));
        assertSame(ise, e);
        final Error error = new OutOfMemoryError();
        e = assertThrows(OutOfMemoryError.class, () -> predicate.test(testable, error));
        assertSame(error, e);
        final IOException ioe = new IOException("Unknown I/O error");
        testable.setThrowable(ioe);
        e = assertThrows(UncheckedIOException.class, () -> predicate.test(testable, ioe));
        final Throwable t = e.getCause();
        assertNotNull(t);
        assertSame(ioe, t);
        testable.setThrowable(null);
        assertTrue(predicate.test(testable, null));
    }

    @Test
    void testAsSupplierWithGet() {
        FailureOnOddInvocations.invocation = 0;
        final FailableSupplier<FailureOnOddInvocations, Throwable> failableSupplier = FailureOnOddInvocations::new;
        final Supplier<FailureOnOddInvocations> supplier = Functions.asSupplier(failableSupplier);
        UndeclaredThrowableException e = assertThrows(UndeclaredThrowableException.class, supplier::get);
        final Throwable cause = e.getCause();
        assertNotNull(cause);
        assertTrue(cause instanceof SomeException);
        assertEquals("Odd Invocation: 1", cause.getMessage());
        final FailureOnOddInvocations instance = supplier.get();
        assertNotNull(instance);
    }
}