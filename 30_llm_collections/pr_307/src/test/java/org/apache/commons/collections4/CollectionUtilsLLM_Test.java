package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollectionUtilsLLM_Test {

    private List<Integer> collectionA;
    private List<Integer> collectionB;
    private Collection<Integer> collectionC;
    private Collection<Integer> collectionD;
    private Collection<Integer> collectionE;
    private Collection<Number> collectionA2;
    private Collection<Number> collectionB2;
    private Collection<Number> collectionC2;
    private Iterable<Integer> iterableA;
    private Iterable<Long> iterableB;
    private Iterable<Integer> iterableC;
    private Iterable<Number> iterableA2;
    private Iterable<Number> iterableB2;
    private final Collection<Integer> emptyCollection = new ArrayList<>(1);

    @BeforeEach
    public void setUp() {
        collectionA = new ArrayList<>();
        collectionA.add(1);
        collectionA.add(2);
        collectionA.add(2);
        collectionA.add(3);
        collectionA.add(3);
        collectionA.add(3);
        collectionA.add(4);
        collectionA.add(4);
        collectionA.add(4);
        collectionA.add(4);
        collectionB = new ArrayList<>();
        collectionB.add(5L);
        collectionB.add(4L);
        collectionB.add(4L);
        collectionB.add(3L);
        collectionB.add(3L);
        collectionB.add(3L);
        collectionB.add(2L);
        collectionB.add(2L);
        collectionB.add(2L);
        collectionB.add(2L);
        collectionC = new ArrayList<>();
        for (final Long l : collectionB) {
            collectionC.add(l.intValue());
        }
        iterableA = collectionA;
        iterableB = collectionB;
        iterableC = collectionC;
        collectionA2 = new ArrayList<>(collectionA);
        collectionB2 = new ArrayList<>(collectionB);
        collectionC2 = new ArrayList<>(collectionC);
        iterableA2 = collectionA2;
        iterableB2 = collectionB2;
        collectionD = new ArrayList<>();
        collectionD.add(1);
        collectionD.add(3);
        collectionD.add(3);
        collectionD.add(3);
        collectionD.add(5);
        collectionD.add(7);
        collectionD.add(7);
        collectionD.add(10);
        collectionE = new ArrayList<>();
        collectionE.add(2);
        collectionE.add(4);
        collectionE.add(4);
        collectionE.add(5);
        collectionE.add(6);
        collectionE.add(6);
        collectionE.add(9);
    }

    @Test
    public void testContainsAllWithObjectsEquals() {
        final Collection<String> coll1 = new ArrayList<>();
        coll1.add("a");
        coll1.add("b");
        coll1.add("c");

        final Collection<String> coll2 = new ArrayList<>();
        coll2.add("a");
        coll2.add("b");

        final Collection<String> coll3 = new ArrayList<>();
        coll3.add("a");
        coll3.add("d");

        assertTrue(CollectionUtils.containsAll(coll1, coll2), "containsAll should return true when all elements are present");
        assertFalse(CollectionUtils.containsAll(coll1, coll3), "containsAll should return false when some elements are missing");
    }
}