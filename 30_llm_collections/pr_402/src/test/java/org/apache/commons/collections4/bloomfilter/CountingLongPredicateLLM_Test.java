package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountingLongPredicateLLM_Test {

    @Test
    void testForEachRemainingExhaustsArray() {
        long[] ary = {1, 2, 3};
        LongBiPredicate func = (a, b) -> a != 0;
        CountingLongPredicate predicate = new CountingLongPredicate(ary, func);

        // Exhaust the array
        predicate.test(0);
        predicate.test(0);
        predicate.test(0);

        // Now forEachRemaining should return true as all elements are exhausted
        assertTrue(predicate.forEachRemaining());
    }

    @Test
    void testForEachRemainingWithNonZeroValues() {
        long[] ary = {1, 2, 3};
        LongBiPredicate func = (a, b) -> a != 0;
        CountingLongPredicate predicate = new CountingLongPredicate(ary, func);

        // Exhaust the array
        predicate.test(0);
        predicate.test(0);

        // Now forEachRemaining should return false as not all elements are exhausted
        assertFalse(predicate.forEachRemaining());
    }

    @Test
    void testForEachRemainingWithZeroValues() {
        long[] ary = {0, 0, 0};
        LongBiPredicate func = (a, b) -> a == 0;
        CountingLongPredicate predicate = new CountingLongPredicate(ary, func);

        // forEachRemaining should return true as all elements are zero and match the predicate
        assertTrue(predicate.forEachRemaining());
    }
}