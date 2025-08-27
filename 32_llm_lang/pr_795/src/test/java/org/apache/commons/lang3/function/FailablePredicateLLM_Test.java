package org.apache.commons.lang3.function;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FailablePredicateLLM_Test {

    @Test
    public void testFalsePredicate() {
        FailablePredicate<Object, Throwable> falsePredicate = FailablePredicate.falsePredicate();
        assertFalse(falsePredicate.test(new Object()));
    }

    @Test
    public void testTruePredicate() {
        FailablePredicate<Object, Throwable> truePredicate = FailablePredicate.truePredicate();
        assertTrue(truePredicate.test(new Object()));
    }

    @Test
    public void testAndPredicate() throws Throwable {
        FailablePredicate<Object, Throwable> truePredicate = FailablePredicate.truePredicate();
        FailablePredicate<Object, Throwable> falsePredicate = FailablePredicate.falsePredicate();
        assertFalse(truePredicate.and(falsePredicate).test(new Object()));
        assertTrue(truePredicate.and(truePredicate).test(new Object()));
    }

    @Test
    public void testNegatePredicate() throws Throwable {
        FailablePredicate<Object, Throwable> truePredicate = FailablePredicate.truePredicate();
        FailablePredicate<Object, Throwable> falsePredicate = FailablePredicate.falsePredicate();
        assertFalse(truePredicate.negate().test(new Object()));
        assertTrue(falsePredicate.negate().test(new Object()));
    }

    @Test
    public void testOrPredicate() throws Throwable {
        FailablePredicate<Object, Throwable> truePredicate = FailablePredicate.truePredicate();
        FailablePredicate<Object, Throwable> falsePredicate = FailablePredicate.falsePredicate();
        assertTrue(truePredicate.or(falsePredicate).test(new Object()));
        assertFalse(falsePredicate.or(falsePredicate).test(new Object()));
    }
}