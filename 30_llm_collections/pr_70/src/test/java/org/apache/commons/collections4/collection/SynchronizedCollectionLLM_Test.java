package org.apache.commons.collections4.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

public class SynchronizedCollectionLLM_Test<E> extends AbstractCollectionTest<E> {

    public SynchronizedCollectionTest2(final String testName) {
        super(testName);
    }

    @Override
    public Collection<E> makeObject() {
        return SynchronizedCollection.synchronizedCollection(new ArrayList<E>());
    }

    @Override
    public Collection<E> makeConfirmedCollection() {
        return new ArrayList<>();
    }

    @Override
    public Collection<E> makeConfirmedFullCollection() {
        final ArrayList<E> list = new ArrayList<>();
        list.addAll(Arrays.asList(getFullElements()));
        return list;
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    // Test for the new removeIf method
    public void testRemoveIf() {
        final Collection<E> collection = makeObject();
        final Collection<E> confirmed = makeConfirmedCollection();

        // Adding elements to both collections
        collection.add((E) "one");
        collection.add((E) "two");
        collection.add((E) "three");
        confirmed.add((E) "one");
        confirmed.add((E) "two");
        confirmed.add((E) "three");

        // Predicate to remove elements containing 'o'
        Predicate<E> predicate = e -> e.toString().contains("o");

        // Removing elements using removeIf
        boolean result = collection.removeIf(predicate);
        boolean confirmedResult = confirmed.removeIf(predicate);

        // Asserting the results
        assertEquals(confirmedResult, result);
        assertEquals(confirmed, collection);
    }
}