package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.collection.PredicatedCollection;
import org.apache.commons.collections4.collection.SynchronizedCollection;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.functors.DefaultEquator;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.junit.Before;
import org.junit.Test;

public class CollectionUtilsLLM_Test {

    private List<Integer> collectionA = null;
    private List<Long> collectionB = null;
    private Collection<Integer> collectionC = null;
    private Collection<Integer> collectionD = null;
    private Collection<Integer> collectionE = null;
    private Collection<Number> collectionA2 = null;
    private Collection<Number> collectionB2 = null;
    private Collection<Number> collectionC2 = null;
    private Iterable<Integer> iterableA = null;
    private Iterable<Long> iterableB = null;
    private Iterable<Integer> iterableC = null;
    private Iterable<Number> iterableA2 = null;
    private Iterable<Number> iterableB2 = null;
    private final Collection<Integer> emptyCollection = new ArrayList<>(1);

    @Before
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
    public void testCardinalityHelperConstructorAccessibility() {
        // Test that CardinalityHelper constructor is package-private
        try {
            Class<?> clazz = Class.forName("org.apache.commons.collections4.CollectionUtils$CardinalityHelper");
            clazz.getDeclaredConstructor(Iterable.class, Iterable.class).setAccessible(true);
        } catch (Exception e) {
            fail("CardinalityHelper constructor should be accessible within the package.");
        }
    }

    @Test
    public void testSetOperationCardinalityHelperConstructorAccessibility() {
        // Test that SetOperationCardinalityHelper constructor is package-private
        try {
            Class<?> clazz = Class.forName("org.apache.commons.collections4.CollectionUtils$SetOperationCardinalityHelper");
            clazz.getDeclaredConstructor(Iterable.class, Iterable.class).setAccessible(true);
        } catch (Exception e) {
            fail("SetOperationCardinalityHelper constructor should be accessible within the package.");
        }
    }

    @Test
    public void testEquatorWrapperConstructorAccessibility() {
        // Test that EquatorWrapper constructor is package-private
        try {
            Class<?> clazz = Class.forName("org.apache.commons.collections4.CollectionUtils$EquatorWrapper");
            clazz.getDeclaredConstructor(Equator.class, Object.class).setAccessible(true);
        } catch (Exception e) {
            fail("EquatorWrapper constructor should be accessible within the package.");
        }
    }

    @Test
    public void testIsEqualCollectionFormatting() {
        // Test the formatting changes in isEqualCollection
        assertTrue(CollectionUtils.isEqualCollection(collectionA, collectionA));
        assertFalse(CollectionUtils.isEqualCollection(collectionA, collectionC));
    }

    @Test
    public void testGetMethodFormatting() {
        // Test the formatting changes in get methods
        final Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertEquals("value1", CollectionUtils.get(map, 0).getValue());
        assertEquals("value2", CollectionUtils.get(map, 1).getValue());

        try {
            CollectionUtils.get(map, 2);
            fail("Expecting IndexOutOfBoundsException.");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testRemoveAllFormatting() {
        // Test the formatting changes in removeAll method
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");
        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        assertEquals(1, result.size());
        assertFalse(result.contains("A"));
        assertTrue(result.contains("B"));
        assertFalse(result.contains("C"));
    }
}