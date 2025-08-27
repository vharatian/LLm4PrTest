package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CountingLongPredicateLLM_Test {

    @FunctionalInterface
    interface LongBiPredicate {
        boolean test(long a, long b);
    }

    @Test
    void testPredicateWithExhaustedArray() {
        long[] ary = {1, 2, 3};
        LongBiPredicate func = (a, b) -> a == 0 && b == 0;
        CountingLongPredicate predicate = new CountingLongPredicate(ary, func);

        // Exhaust the array
        predicate.test(0);
        predicate.test(0);
        predicate.test(0);

        // Now the array is exhausted, subsequent calls should use zero value
        assertTrue(predicate.test(0));
    }

    @Test
    void testForEachRemainingWithNonExhaustedArray() {
        long[] ary = {1, 2, 3};
        LongBiPredicate func = (a, b) -> a != 0;
        CountingLongPredicate predicate = new CountingLongPredicate(ary, func);

        // Call forEachRemaining before exhausting the array
        assertFalse(predicate.forEachRemaining());
    }

    @Test
    void testForEachRemainingWithExhaustedArray() {
        long[] ary = {1, 2, 3};
        LongBiPredicate func = (a, b) -> a == 0;
        CountingLongPredicate predicate = new CountingLongPredicate(ary, func);

        // Exhaust the array
        predicate.test(0);
        predicate.test(0);
        predicate.test(0);

        // Now the array is exhausted, forEachRemaining should return true
        assertTrue(predicate.forEachRemaining());
    }
}