package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.bag.HashBag;
import org.junit.Before;
import org.junit.Test;

public class CollectionUtilsLLM_Test {

    private List<Integer> collectionA;
    private List<Long> collectionB;
    private Collection<Integer> collectionC;
    private Iterable<Integer> iterableA;
    private Iterable<Long> iterableB;
    private Iterable<Integer> iterableC;

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
    }

    @Test(expected = NullPointerException.class)
    public void testUnionWithNullFirstCollection() {
        CollectionUtils.union(null, iterableC);
    }

    @Test(expected = NullPointerException.class)
    public void testUnionWithNullSecondCollection() {
        CollectionUtils.union(iterableA, null);
    }

    @Test(expected = NullPointerException.class)
    public void testIntersectionWithNullFirstCollection() {
        CollectionUtils.intersection(null, iterableC);
    }

    @Test(expected = NullPointerException.class)
    public void testIntersectionWithNullSecondCollection() {
        CollectionUtils.intersection(iterableA, null);
    }

    @Test(expected = NullPointerException.class)
    public void testDisjunctionWithNullFirstCollection() {
        CollectionUtils.disjunction(null, iterableC);
    }

    @Test(expected = NullPointerException.class)
    public void testDisjunctionWithNullSecondCollection() {
        CollectionUtils.disjunction(iterableA, null);
    }

    @Test(expected = NullPointerException.class)
    public void testSubtractWithNullFirstCollection() {
        CollectionUtils.subtract(null, iterableC, TruePredicate.truePredicate());
    }

    @Test(expected = NullPointerException.class)
    public void testSubtractWithNullSecondCollection() {
        CollectionUtils.subtract(iterableA, null, TruePredicate.truePredicate());
    }

    @Test(expected = NullPointerException.class)
    public void testSubtractWithNullPredicate() {
        CollectionUtils.subtract(iterableA, iterableC, null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNullFirstCollection() {
        CollectionUtils.containsAll(null, collectionC);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNullSecondCollection() {
        CollectionUtils.containsAll(collectionA, null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAnyWithNullFirstCollection() {
        CollectionUtils.containsAny(null, collectionC);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAnyWithNullSecondCollection() {
        CollectionUtils.containsAny(collectionA, null);
    }

    @Test(expected = NullPointerException.class)
    public void testGetCardinalityMapWithNullCollection() {
        CollectionUtils.getCardinalityMap(null);
    }

    @Test(expected = NullPointerException.class)
    public void testIsSubCollectionWithNullFirstCollection() {
        CollectionUtils.isSubCollection(null, collectionC);
    }

    @Test(expected = NullPointerException.class)
    public void testIsSubCollectionWithNullSecondCollection() {
        CollectionUtils.isSubCollection(collectionA, null);
    }

    @Test(expected = NullPointerException.class)
    public void testIsProperSubCollectionWithNullFirstCollection() {
        CollectionUtils.isProperSubCollection(null, collectionC);
    }

    @Test(expected = NullPointerException.class)
    public void testIsProperSubCollectionWithNullSecondCollection() {
        CollectionUtils.isProperSubCollection(collectionA, null);
    }

    @Test(expected = NullPointerException.class)
    public void testIsEqualCollectionWithNullFirstCollection() {
        CollectionUtils.isEqualCollection(null, collectionC);
    }

    @Test(expected = NullPointerException.class)
    public void testIsEqualCollectionWithNullSecondCollection() {
        CollectionUtils.isEqualCollection(collectionA, null);
    }

    @Test(expected = NullPointerException.class)
    public void testIsEqualCollectionWithNullEquator() {
        CollectionUtils.isEqualCollection(collectionA, collectionC, null);
    }

    @Test(expected = NullPointerException.class)
    public void testSelectWithNullInputCollection() {
        CollectionUtils.select(null, TruePredicate.truePredicate());
    }

    @Test(expected = NullPointerException.class)
    public void testSelectRejectedWithNullInputCollection() {
        CollectionUtils.selectRejected(null, TruePredicate.truePredicate());
    }

    @Test(expected = NullPointerException.class)
    public void testCollectWithNullInputCollection() {
        CollectionUtils.collect(null, TransformerUtils.nopTransformer());
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllWithNullCollection() {
        CollectionUtils.addAll(null, iterableA);
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllWithNullIterable() {
        CollectionUtils.addAll(collectionA, (Iterable<Integer>) null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllWithNullIterator() {
        CollectionUtils.addAll(collectionA, (Iterator<Integer>) null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllWithNullEnumeration() {
        CollectionUtils.addAll(collectionA, (Enumeration<Integer>) null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllWithNullElements() {
        CollectionUtils.addAll(collectionA, (Integer[]) null);
    }

    @Test(expected = NullPointerException.class)
    public void testGetWithNullIterator() {
        CollectionUtils.get((Iterator<Integer>) null, 0);
    }

    @Test(expected = NullPointerException.class)
    public void testGetWithNullIterable() {
        CollectionUtils.get((Iterable<Integer>) null, 0);
    }

    @Test(expected = NullPointerException.class)
    public void testGetWithNullMap() {
        CollectionUtils.get((Map<Integer, Integer>) null, 0);
    }

    @Test(expected = NullPointerException.class)
    public void testReverseArrayWithNullArray() {
        CollectionUtils.reverseArray(null);
    }

    @Test(expected = NullPointerException.class)
    public void testPermutationsWithNullCollection() {
        CollectionUtils.permutations(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllWithNullCollection() {
        CollectionUtils.retainAll(null, collectionC);
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllWithNullRetain() {
        CollectionUtils.retainAll(collectionA, null);
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllWithNullEquator() {
        CollectionUtils.retainAll(collectionA, collectionC, null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveRangeWithNullInput() {
        CollectionUtils.removeRange(null, 0, 1);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveCountWithNullInput() {
        CollectionUtils.removeCount(null, 0, 1);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNullCollection() {
        CollectionUtils.removeAll(null, collectionC);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNullRemove() {
        CollectionUtils.removeAll(collectionA, null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNullEquator() {
        CollectionUtils.removeAll(collectionA, collectionC, null);
    }

    @Test(expected = NullPointerException.class)
    public void testSynchronizedCollectionWithNullCollection() {
        CollectionUtils.synchronizedCollection(null);
    }

    @Test(expected = NullPointerException.class)
    public void testUnmodifiableCollectionWithNullCollection() {
        CollectionUtils.unmodifiableCollection(null);
    }

    @Test(expected = NullPointerException.class)
    public void testPredicatedCollectionWithNullCollection() {
        CollectionUtils.predicatedCollection(null, TruePredicate.truePredicate());
    }

    @Test(expected = NullPointerException.class)
    public void testPredicatedCollectionWithNullPredicate() {
        CollectionUtils.predicatedCollection(collectionA, null);
    }

    @Test(expected = NullPointerException.class)
    public void testTransformingCollectionWithNullCollection() {
        CollectionUtils.transformingCollection(null, TransformerUtils.nopTransformer());
    }

    @Test(expected = NullPointerException.class)
    public void testTransformingCollectionWithNullTransformer() {
        CollectionUtils.transformingCollection(collectionA, null);
    }
}