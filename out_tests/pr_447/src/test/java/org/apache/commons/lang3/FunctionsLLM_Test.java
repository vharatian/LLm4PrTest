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
import static org.junit.jupiter.api.Assertions.assertFalse;

class FunctionsLLM_Test {

    @Test
    void testAsRunnableWithNull() {
        assertThrows(NullPointerException.class, () -> Functions.asRunnable(null));
    }

    @Test
    void testAsConsumerWithNull() {
        assertThrows(NullPointerException.class, () -> Functions.asConsumer(null));
    }

    @Test
    void testAsCallableWithNull() {
        assertThrows(NullPointerException.class, () -> Functions.asCallable(null));
    }

    @Test
    void testAsBiConsumerWithNull() {
        assertThrows(NullPointerException.class, () -> Functions.asBiConsumer(null));
    }

    @Test
    void testAsFunctionWithNull() {
        assertThrows(NullPointerException.class, () -> Functions.asFunction(null));
    }

    @Test
    void testAsBiFunctionWithNull() {
        assertThrows(NullPointerException.class, () -> Functions.asBiFunction(null));
    }

    @Test
    void testAsPredicateWithNull() {
        assertThrows(NullPointerException.class, () -> Functions.asPredicate(null));
    }

    @Test
    void testAsBiPredicateWithNull() {
        assertThrows(NullPointerException.class, () -> Functions.asBiPredicate(null));
    }

    @Test
    void testAsSupplierWithNull() {
        assertThrows(NullPointerException.class, () -> Functions.asSupplier(null));
    }

    @Test
    void testGetWithNullSupplier() {
        assertThrows(NullPointerException.class, () -> Functions.get(null));
    }
}