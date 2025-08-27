package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Predicate;

public class ComparableUtilsLLM_Test {

    @Test
    public void testIsEqualTo() {
        assertTrue(ComparableUtils.is(5).equalTo(5));
        assertFalse(ComparableUtils.is(5).equalTo(3));
    }

    @Test
    public void testIsLessThan() {
        assertTrue(ComparableUtils.is(3).lessThan(5));
        assertFalse(ComparableUtils.is(5).lessThan(3));
    }

    @Test
    public void testIsLessThanOrEqualTo() {
        assertTrue(ComparableUtils.is(3).lessThanOrEqualTo(5));
        assertTrue(ComparableUtils.is(5).lessThanOrEqualTo(5));
        assertFalse(ComparableUtils.is(5).lessThanOrEqualTo(3));
    }

    @Test
    public void testIsGreaterThan() {
        assertTrue(ComparableUtils.is(5).greaterThan(3));
        assertFalse(ComparableUtils.is(3).greaterThan(5));
    }

    @Test
    public void testIsGreaterThanOrEqualTo() {
        assertTrue(ComparableUtils.is(5).greaterThanOrEqualTo(3));
        assertTrue(ComparableUtils.is(5).greaterThanOrEqualTo(5));
        assertFalse(ComparableUtils.is(3).greaterThanOrEqualTo(5));
    }

    @Test
    public void testIsBetween() {
        assertTrue(ComparableUtils.is(5).between(3, 7));
        assertTrue(ComparableUtils.is(5).between(7, 3));
        assertFalse(ComparableUtils.is(2).between(3, 7));
    }

    @Test
    public void testIsBetweenExclusive() {
        assertTrue(ComparableUtils.is(5).betweenExclusive(3, 7));
        assertTrue(ComparableUtils.is(5).betweenExclusive(7, 3));
        assertFalse(ComparableUtils.is(3).betweenExclusive(3, 7));
        assertFalse(ComparableUtils.is(7).betweenExclusive(3, 7));
    }

    @Test
    public void testLtPredicate() {
        Predicate<Integer> predicate = ComparableUtils.lt(5);
        assertTrue(predicate.test(3));
        assertFalse(predicate.test(5));
        assertFalse(predicate.test(7));
    }

    @Test
    public void testLePredicate() {
        Predicate<Integer> predicate = ComparableUtils.le(5);
        assertTrue(predicate.test(3));
        assertTrue(predicate.test(5));
        assertFalse(predicate.test(7));
    }

    @Test
    public void testGtPredicate() {
        Predicate<Integer> predicate = ComparableUtils.gt(5);
        assertFalse(predicate.test(3));
        assertFalse(predicate.test(5));
        assertTrue(predicate.test(7));
    }

    @Test
    public void testGePredicate() {
        Predicate<Integer> predicate = ComparableUtils.ge(5);
        assertFalse(predicate.test(3));
        assertTrue(predicate.test(5));
        assertTrue(predicate.test(7));
    }

    @Test
    public void testBetweenPredicate() {
        Predicate<Integer> predicate = ComparableUtils.between(3, 7);
        assertTrue(predicate.test(5));
        assertTrue(predicate.test(3));
        assertTrue(predicate.test(7));
        assertFalse(predicate.test(2));
        assertFalse(predicate.test(8));
    }

    @Test
    public void testBetweenExclusivePredicate() {
        Predicate<Integer> predicate = ComparableUtils.betweenExclusive(3, 7);
        assertTrue(predicate.test(5));
        assertFalse(predicate.test(3));
        assertFalse(predicate.test(7));
        assertFalse(predicate.test(2));
        assertFalse(predicate.test(8));
    }
}