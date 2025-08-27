package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.BiPredicate;

class CountingPredicateLLM_Test {

    @Test
    void testPredicateWithMatchingElements() {
        Integer[] array = {1, 2, 3};
        BiPredicate<Integer, Integer> func = (a, b) -> a.equals(b);
        CountingPredicate<Integer> predicate = new CountingPredicate<>(array, func);

        assertTrue(predicate.test(1));
        assertTrue(predicate.test(2));
        assertTrue(predicate.test(3));
    }

    @Test
    void testPredicateWithNonMatchingElements() {
        Integer[] array = {1, 2, 3};
        BiPredicate<Integer, Integer> func = (a, b) -> a.equals(b);
        CountingPredicate<Integer> predicate = new CountingPredicate<>(array, func);

        assertFalse(predicate.test(4));
        assertFalse(predicate.test(5));
        assertFalse(predicate.test(6));
    }

    @Test
    void testPredicateWithExhaustedArray() {
        Integer[] array = {1, 2, 3};
        BiPredicate<Integer, Integer> func = (a, b) -> a == null && b == null;
        CountingPredicate<Integer> predicate = new CountingPredicate<>(array, func);

        predicate.test(1);
        predicate.test(2);
        predicate.test(3);

        assertTrue(predicate.test(null));
    }

    @Test
    void testForEachRemainingWithAllMatching() {
        Integer[] array = {1, 2, 3};
        BiPredicate<Integer, Integer> func = (a, b) -> a != null;
        CountingPredicate<Integer> predicate = new CountingPredicate<>(array, func);

        predicate.test(1);
        predicate.test(2);

        assertTrue(predicate.forEachRemaining());
    }

    @Test
    void testForEachRemainingWithNonMatching() {
        Integer[] array = {1, 2, 3};
        BiPredicate<Integer, Integer> func = (a, b) -> a != null;
        CountingPredicate<Integer> predicate = new CountingPredicate<>(array, func);

        predicate.test(1);
        predicate.test(2);
        predicate.test(3);

        assertFalse(predicate.forEachRemaining());
    }
}