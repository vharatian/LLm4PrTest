package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

public class CollectionUtilsLLM_Test {

    @Test
    public void testRemoveRange() {
        Collection<Integer> collection = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collection.add(i);
        }

        Collection<Integer> removed = CollectionUtils.removeRange(collection, 2, 5);
        assertEquals(3, removed.size());
        assertTrue(removed.contains(2));
        assertTrue(removed.contains(3));
        assertTrue(removed.contains(4));
        assertEquals(7, collection.size());
        assertTrue(collection.contains(0));
        assertTrue(collection.contains(1));
        assertTrue(collection.contains(5));
        assertTrue(collection.contains(6));
        assertTrue(collection.contains(7));
        assertTrue(collection.contains(8));
        assertTrue(collection.contains(9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveRangeWithNullCollection() {
        CollectionUtils.removeRange(null, 2, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveRangeWithEndIndexLessThanStartIndex() {
        Collection<Integer> collection = new ArrayList<>();
        CollectionUtils.removeRange(collection, 5, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveRangeWithEndIndexGreaterThanSize() {
        Collection<Integer> collection = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            collection.add(i);
        }
        CollectionUtils.removeRange(collection, 2, 6);
    }

    @Test
    public void testRemoveCount() {
        Collection<Integer> collection = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collection.add(i);
        }

        Collection<Integer> removed = CollectionUtils.removeCount(collection, 2, 3);
        assertEquals(3, removed.size());
        assertTrue(removed.contains(2));
        assertTrue(removed.contains(3));
        assertTrue(removed.contains(4));
        assertEquals(7, collection.size());
        assertTrue(collection.contains(0));
        assertTrue(collection.contains(1));
        assertTrue(collection.contains(5));
        assertTrue(collection.contains(6));
        assertTrue(collection.contains(7));
        assertTrue(collection.contains(8));
        assertTrue(collection.contains(9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCountWithNullCollection() {
        CollectionUtils.removeCount(null, 2, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveCountWithStartIndexLessThanZero() {
        Collection<Integer> collection = new ArrayList<>();
        CollectionUtils.removeCount(collection, -1, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveCountWithCountLessThanZero() {
        Collection<Integer> collection = new ArrayList<>();
        CollectionUtils.removeCount(collection, 2, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveCountWithStartIndexPlusCountGreaterThanSize() {
        Collection<Integer> collection = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            collection.add(i);
        }
        CollectionUtils.removeCount(collection, 2, 4);
    }
}