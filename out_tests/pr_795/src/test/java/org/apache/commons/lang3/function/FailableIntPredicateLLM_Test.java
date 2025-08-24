package org.apache.commons.lang3.function;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FailableIntPredicateLLM_Test {

    @Test
    public void testFalsePredicate() {
        FailableIntPredicate<Throwable> falsePredicate = FailableIntPredicate.falsePredicate();
        assertFalse(falsePredicate.test(0));
        assertFalse(falsePredicate.test(1));
    }

    @Test
    public void testTruePredicate() {
        FailableIntPredicate<Throwable> truePredicate = FailableIntPredicate.truePredicate();
        assertTrue(truePredicate.test(0));
        assertTrue(truePredicate.test(1));
    }

    @Test
    public void testAndPredicate() throws Throwable {
        FailableIntPredicate<Throwable> truePredicate = FailableIntPredicate.truePredicate();
        FailableIntPredicate<Throwable> falsePredicate = FailableIntPredicate.falsePredicate();
        FailableIntPredicate<Throwable> andPredicate = truePredicate.and(falsePredicate);

        assertFalse(andPredicate.test(0));
        assertFalse(andPredicate.test(1));
    }

    @Test
    public void testNegatePredicate() throws Throwable {
        FailableIntPredicate<Throwable> truePredicate = FailableIntPredicate.truePredicate();
        FailableIntPredicate<Throwable> negatePredicate = truePredicate.negate();

        assertFalse(negatePredicate.test(0));
        assertFalse(negatePredicate.test(1));
    }

    @Test
    public void testOrPredicate() throws Throwable {
        FailableIntPredicate<Throwable> truePredicate = FailableIntPredicate.truePredicate();
        FailableIntPredicate<Throwable> falsePredicate = FailableIntPredicate.falsePredicate();
        FailableIntPredicate<Throwable> orPredicate = truePredicate.or(falsePredicate);

        assertTrue(orPredicate.test(0));
        assertTrue(orPredicate.test(1));
    }
}