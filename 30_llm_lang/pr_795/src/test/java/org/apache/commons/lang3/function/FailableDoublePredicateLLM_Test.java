package org.apache.commons.lang3.function;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FailableDoublePredicateLLM_Test {

    @Test
    public void testFalsePredicate() {
        FailableDoublePredicate<Throwable> falsePredicate = FailableDoublePredicate.falsePredicate();
        assertFalse(falsePredicate.test(0.0));
        assertFalse(falsePredicate.test(1.0));
    }

    @Test
    public void testTruePredicate() {
        FailableDoublePredicate<Throwable> truePredicate = FailableDoublePredicate.truePredicate();
        assertTrue(truePredicate.test(0.0));
        assertTrue(truePredicate.test(1.0));
    }

    @Test
    public void testAndPredicate() throws Throwable {
        FailableDoublePredicate<Throwable> truePredicate = FailableDoublePredicate.truePredicate();
        FailableDoublePredicate<Throwable> falsePredicate = FailableDoublePredicate.falsePredicate();
        assertFalse(truePredicate.and(falsePredicate).test(0.0));
        assertFalse(falsePredicate.and(truePredicate).test(0.0));
        assertTrue(truePredicate.and(truePredicate).test(0.0));
    }

    @Test
    public void testNegatePredicate() throws Throwable {
        FailableDoublePredicate<Throwable> truePredicate = FailableDoublePredicate.truePredicate();
        FailableDoublePredicate<Throwable> falsePredicate = FailableDoublePredicate.falsePredicate();
        assertFalse(truePredicate.negate().test(0.0));
        assertTrue(falsePredicate.negate().test(0.0));
    }

    @Test
    public void testOrPredicate() throws Throwable {
        FailableDoublePredicate<Throwable> truePredicate = FailableDoublePredicate.truePredicate();
        FailableDoublePredicate<Throwable> falsePredicate = FailableDoublePredicate.falsePredicate();
        assertTrue(truePredicate.or(falsePredicate).test(0.0));
        assertTrue(falsePredicate.or(truePredicate).test(0.0));
        assertFalse(falsePredicate.or(falsePredicate).test(0.0));
    }
}