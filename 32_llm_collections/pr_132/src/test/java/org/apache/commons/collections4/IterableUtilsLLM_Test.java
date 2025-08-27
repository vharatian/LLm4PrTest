package org.apache.commons.collections4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IterableUtilsLLM_Test {

    private Iterable<Integer> iterableA = null;
    private Iterable<Long> iterableB = null;
    private Iterable<Integer> emptyIterable = null;

    @Before
    public void setUp() {
        final Collection<Integer> collectionA = new ArrayList<>();
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
        iterableA = collectionA;

        final Collection<Long> collectionB = new LinkedList<>();
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
        iterableB = collectionB;

        emptyIterable = Collections.emptyList();
    }

    @Test
    public void testUnmodifiableIterableConstructor() {
        // Create a sample iterable
        final List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        // Create an instance of UnmodifiableIterable using the package-private constructor
        IterableUtils.UnmodifiableIterable<String> unmodifiableIterable = new IterableUtils.UnmodifiableIterable<>(list);

        // Verify that the unmodifiable iterable is not null
        assertNotNull(unmodifiableIterable);

        // Verify that the unmodifiable iterable contains the same elements as the original list
        List<String> resultList = new ArrayList<>();
        for (String item : unmodifiableIterable) {
            resultList.add(item);
        }
        assertTrue(resultList.containsAll(list));
    }
}