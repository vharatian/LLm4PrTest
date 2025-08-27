package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LongBiPredicateLLM_Test {

    @Test
    public void testTrueCondition() {
        LongBiPredicate predicate = (x, y) -> x > y;
        assertTrue(predicate.test(10L, 5L));
    }

    @Test
    public void testFalseCondition() {
        LongBiPredicate predicate = (x, y) -> x > y;
        assertFalse(predicate.test(5L, 10L));
    }

    @Test
    public void testEqualCondition() {
        LongBiPredicate predicate = (x, y) -> x == y;
        assertTrue(predicate.test(5L, 5L));
        assertFalse(predicate.test(5L, 10L));
    }

    @Test
    public void testNegativeValues() {
        LongBiPredicate predicate = (x, y) -> x < y;
        assertTrue(predicate.test(-10L, -5L));
        assertFalse(predicate.test(-5L, -10L));
    }

    @Test
    public void testZeroValues() {
        LongBiPredicate predicate = (x, y) -> x == y;
        assertTrue(predicate.test(0L, 0L));
        assertFalse(predicate.test(0L, 1L));
    }
}