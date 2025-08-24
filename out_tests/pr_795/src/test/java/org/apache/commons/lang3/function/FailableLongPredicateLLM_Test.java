package org.apache.commons.lang3.function;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FailableLongPredicateLLM_Test {

    @Test
    public void testFalsePredicate() {
        FailableLongPredicate<Throwable> falsePredicate = FailableLongPredicate.falsePredicate();
        assertFalse(falsePredicate.test(0));
        assertFalse(falsePredicate.test(1));
        assertFalse(falsePredicate.test(-1));
    }

    @Test
    public void testTruePredicate() {
        FailableLongPredicate<Throwable> truePredicate = FailableLongPredicate.truePredicate();
        assertTrue(truePredicate.test(0));
        assertTrue(truePredicate.test(1));
        assertTrue(truePredicate.test(-1));
    }

    @Test
    public void testAndPredicate() throws Throwable {
        FailableLongPredicate<Throwable> truePredicate = FailableLongPredicate.truePredicate();
        FailableLongPredicate<Throwable> falsePredicate = FailableLongPredicate.falsePredicate();

        FailableLongPredicate<Throwable> andPredicate = truePredicate.and(falsePredicate);
        assertFalse(andPredicate.test(0));
        assertFalse(andPredicate.test(1));
        assertFalse(andPredicate.test(-1));

        andPredicate = truePredicate.and(truePredicate);
        assertTrue(andPredicate.test(0));
        assertTrue(andPredicate.test(1));
        assertTrue(andPredicate.test(-1));
    }

    @Test
    public void testNegatePredicate() throws Throwable {
        FailableLongPredicate<Throwable> truePredicate = FailableLongPredicate.truePredicate();
        FailableLongPredicate<Throwable> negatePredicate = truePredicate.negate();
        assertFalse(negatePredicate.test(0));
        assertFalse(negatePredicate.test(1));
        assertFalse(negatePredicate.test(-1));
    }

    @Test
    public void testOrPredicate() throws Throwable {
        FailableLongPredicate<Throwable> truePredicate = FailableLongPredicate.truePredicate();
        FailableLongPredicate<Throwable> falsePredicate = FailableLongPredicate.falsePredicate();

        FailableLongPredicate<Throwable> orPredicate = truePredicate.or(falsePredicate);
        assertTrue(orPredicate.test(0));
        assertTrue(orPredicate.test(1));
        assertTrue(orPredicate.test(-1));

        orPredicate = falsePredicate.or(falsePredicate);
        assertFalse(orPredicate.test(0));
        assertFalse(orPredicate.test(1));
        assertFalse(orPredicate.test(-1));
    }
}