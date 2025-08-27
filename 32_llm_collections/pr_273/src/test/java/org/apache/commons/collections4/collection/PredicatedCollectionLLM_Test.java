package org.apache.commons.collections4.collection;

import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Queue;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.PredicatedBag;
import org.apache.commons.collections4.list.PredicatedList;
import org.apache.commons.collections4.multiset.HashMultiSet;
import org.apache.commons.collections4.multiset.PredicatedMultiSet;
import org.apache.commons.collections4.queue.PredicatedQueue;
import org.apache.commons.collections4.set.PredicatedSet;
import org.junit.Test;
import static org.junit.Assert.*;

public class PredicatedCollectionLLM_Test<E> {

    private final Predicate<E> truePredicate = e -> true;
    private final Predicate<E> falsePredicate = e -> false;

    @Test
    public void testCreatePredicatedList() {
        PredicatedCollection.Builder<E> builder = new PredicatedCollection.Builder<>(truePredicate);
        List<E> list = builder.createPredicatedList();
        assertTrue(list instanceof PredicatedList);
    }

    @Test
    public void testCreatePredicatedSet() {
        PredicatedCollection.Builder<E> builder = new PredicatedCollection.Builder<>(truePredicate);
        Set<E> set = builder.createPredicatedSet();
        assertTrue(set instanceof PredicatedSet);
    }

    @Test
    public void testCreatePredicatedMultiSet() {
        PredicatedCollection.Builder<E> builder = new PredicatedCollection.Builder<>(truePredicate);
        MultiSet<E> multiSet = builder.createPredicatedMultiSet();
        assertTrue(multiSet instanceof PredicatedMultiSet);
    }

    @Test
    public void testCreatePredicatedBag() {
        PredicatedCollection.Builder<E> builder = new PredicatedCollection.Builder<>(truePredicate);
        Bag<E> bag = builder.createPredicatedBag();
        assertTrue(bag instanceof PredicatedBag);
    }

    @Test
    public void testCreatePredicatedQueue() {
        PredicatedCollection.Builder<E> builder = new PredicatedCollection.Builder<>(truePredicate);
        Queue<E> queue = builder.createPredicatedQueue();
        assertTrue(queue instanceof PredicatedQueue);
    }

    @Test
    public void testCreatePredicatedListWithFalsePredicate() {
        PredicatedCollection.Builder<E> builder = new PredicatedCollection.Builder<>(falsePredicate);
        List<E> list = builder.createPredicatedList();
        assertTrue(list instanceof PredicatedList);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testCreatePredicatedSetWithFalsePredicate() {
        PredicatedCollection.Builder<E> builder = new PredicatedCollection.Builder<>(falsePredicate);
        Set<E> set = builder.createPredicatedSet();
        assertTrue(set instanceof PredicatedSet);
        assertTrue(set.isEmpty());
    }

    @Test
    public void testCreatePredicatedMultiSetWithFalsePredicate() {
        PredicatedCollection.Builder<E> builder = new PredicatedCollection.Builder<>(falsePredicate);
        MultiSet<E> multiSet = builder.createPredicatedMultiSet();
        assertTrue(multiSet instanceof PredicatedMultiSet);
        assertTrue(multiSet.isEmpty());
    }

    @Test
    public void testCreatePredicatedBagWithFalsePredicate() {
        PredicatedCollection.Builder<E> builder = new PredicatedCollection.Builder<>(falsePredicate);
        Bag<E> bag = builder.createPredicatedBag();
        assertTrue(bag instanceof PredicatedBag);
        assertTrue(bag.isEmpty());
    }

    @Test
    public void testCreatePredicatedQueueWithFalsePredicate() {
        PredicatedCollection.Builder<E> builder = new PredicatedCollection.Builder<>(falsePredicate);
        Queue<E> queue = builder.createPredicatedQueue();
        assertTrue(queue instanceof PredicatedQueue);
        assertTrue(queue.isEmpty());
    }
}