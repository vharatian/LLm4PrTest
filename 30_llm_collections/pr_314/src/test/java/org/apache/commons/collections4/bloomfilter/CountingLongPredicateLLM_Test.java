package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountingLongPredicateLLM_Test {

    @FunctionalInterface
    interface LongBiPredicate {
        boolean test(long a, long b);
    }

    @Test
    void testPredicateWithExactArrayLength() {
        long[] ary = {1, 2, 3};
        LongBiPredicate func = (a, b) -> a == b;
        CountingLongPredicate predicate = new CountingLongPredicate(ary, func);

        assertTrue(predicate.test(1));
        assertTrue(predicate.test(2));
        assertTrue(predicate.test(3));
        assertFalse(predicate.test(4)); // ary is exhausted, should compare with 0
    }

    @Test
    void testPredicateWithShorterArrayLength() {
        long[] ary = {1, 2};
        LongBiPredicate func = (a, b) -> a == b;
        CountingLongPredicate predicate = new CountingLongPredicate(ary, func);

        assertTrue(predicate.test(1));
        assertTrue(predicate.test(2));
        assertFalse(predicate.test(3)); // ary is exhausted, should compare with 0
    }

    @Test
    void testForEachRemaining() {
        long[] ary = {1, 2, 3};
        LongBiPredicate func = (a, b) -> a == b || b == 0;
        CountingLongPredicate predicate = new CountingLongPredicate(ary, func);

        predicate.test(1);
        predicate.test(2);

        assertTrue(predicate.forEachRemaining()); // remaining element 3 should be compared with 0
    }

    @Test
    void testForEachRemainingWithExhaustedArray() {
        long[] ary = {1, 2};
        LongBiPredicate func = (a, b) -> a == b || b == 0;
        CountingLongPredicate predicate = new CountingLongPredicate(ary, func);

        predicate.test(1);
        predicate.test(2);

        assertTrue(predicate.forEachRemaining()); // ary is exhausted, should return true
    }

    @Test
    void testForEachRemainingWithNonMatchingPredicate() {
        long[] ary = {1, 2, 3};
        LongBiPredicate func = (a, b) -> a == b;
        CountingLongPredicate predicate = new CountingLongPredicate(ary, func);

        predicate.test(1);
        predicate.test(2);

        assertFalse(predicate.forEachRemaining()); // remaining element 3 should not match with 0
    }
}