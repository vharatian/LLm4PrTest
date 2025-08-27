package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.SetUtils.SetView;
import org.apache.commons.collections4.set.PredicatedSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUtilsLLM_Test {

    private Set<Integer> setA;
    private Set<Integer> setB;

    @BeforeEach
    public void setUp() {
        setA = new HashSet<>();
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.add(4);
        setA.add(5);
        setB = new HashSet<>();
        setB.add(3);
        setB.add(4);
        setB.add(5);
        setB.add(6);
        setB.add(7);
    }

    @Test
    public void testDifference() {
        final SetView<Integer> set = SetUtils.difference(setA, setB);
        assertEquals(2, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        for (final Integer i : setB) {
            assertFalse(set.contains(i));
        }
        final Set<Integer> set2 = SetUtils.difference(setA, SetUtils.<Integer>emptySet());
        assertEquals(setA, set2);
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> SetUtils.difference(setA, null),
                "Expecting NullPointerException"),
            () -> assertThrows(NullPointerException.class, () -> SetUtils.difference(null, setA),
                "Expecting NullPointerException")
        );
    }

    @Test
    public void testDisjunction() {
        final SetView<Integer> set = SetUtils.disjunction(setA, setB);
        assertEquals(4, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(6));
        assertTrue(set.contains(7));
        assertFalse(set.contains(3));
        assertFalse(set.contains(4));
        assertFalse(set.contains(5));
        final Set<Integer> set2 = SetUtils.disjunction(setA, SetUtils.<Integer>emptySet());
        assertEquals(setA, set2);
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> SetUtils.disjunction(setA, null),
                "Expecting NullPointerException"),
            () -> assertThrows(NullPointerException.class, () -> SetUtils.disjunction(null, setA),
                "Expecting NullPointerException")
        );
    }

    @Test
    public void testIntersection() {
        final SetView<Integer> set = SetUtils.intersection(setA, setB);
        assertEquals(3, set.size());
        assertTrue(set.contains(3));
        assertTrue(set.contains(4));
        assertTrue(set.contains(5));
        assertFalse(set.contains(1));
        assertFalse(set.contains(2));
        assertFalse(set.contains(6));
        assertFalse(set.contains(7));
        final Set<Integer> set2 = SetUtils.intersection(setA, SetUtils.<Integer>emptySet());
        assertEquals(SetUtils.<Integer>emptySet(), set2);
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> SetUtils.intersection(setA, null),
                "Expecting NullPointerException"),
            () -> assertThrows(NullPointerException.class, () -> SetUtils.intersection(null, setA),
                "Expecting NullPointerException")
        );
    }

    @Test
    public void testUnion() {
        final SetView<Integer> set = SetUtils.union(setA, setB);
        assertEquals(7, set.size());
        assertTrue(set.containsAll(setA));
        assertTrue(set.containsAll(setB));
        final Set<Integer> set2 = SetUtils.union(setA, SetUtils.<Integer>emptySet());
        assertEquals(setA, set2);
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> SetUtils.union(setA, null),
                "Expecting NullPointerException"),
            () -> assertThrows(NullPointerException.class, () -> SetUtils.union(null, setA),
                "Expecting NullPointerException")
        );
    }
}