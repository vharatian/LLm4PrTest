package org.apache.commons.lang3.function;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FailableBiPredicateLLM_Test {

    @Test
    public void testTruePredicate() {
        FailableBiPredicate<Object, Object, Throwable> truePredicate = FailableBiPredicate.truePredicate();
        assertTrue(truePredicate.test(null, null));
        assertTrue(truePredicate.test(new Object(), new Object()));
    }

    @Test
    public void testFalsePredicate() {
        FailableBiPredicate<Object, Object, Throwable> falsePredicate = FailableBiPredicate.falsePredicate();
        assertFalse(falsePredicate.test(null, null));
        assertFalse(falsePredicate.test(new Object(), new Object()));
    }

    @Test
    public void testAndPredicate() throws Throwable {
        FailableBiPredicate<Object, Object, Throwable> truePredicate = FailableBiPredicate.truePredicate();
        FailableBiPredicate<Object, Object, Throwable> falsePredicate = FailableBiPredicate.falsePredicate();
        FailableBiPredicate<Object, Object, Throwable> andPredicate = truePredicate.and(falsePredicate);
        assertFalse(andPredicate.test(null, null));
    }

    @Test
    public void testNegatePredicate() throws Throwable {
        FailableBiPredicate<Object, Object, Throwable> truePredicate = FailableBiPredicate.truePredicate();
        FailableBiPredicate<Object, Object, Throwable> negatePredicate = truePredicate.negate();
        assertFalse(negatePredicate.test(null, null));
    }

    @Test
    public void testOrPredicate() throws Throwable {
        FailableBiPredicate<Object, Object, Throwable> truePredicate = FailableBiPredicate.truePredicate();
        FailableBiPredicate<Object, Object, Throwable> falsePredicate = FailableBiPredicate.falsePredicate();
        FailableBiPredicate<Object, Object, Throwable> orPredicate = truePredicate.or(falsePredicate);
        assertTrue(orPredicate.test(null, null));
    }
}