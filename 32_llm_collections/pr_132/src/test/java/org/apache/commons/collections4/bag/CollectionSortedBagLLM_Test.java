package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.collection.AbstractCollectionTest;

public class CollectionSortedBagLLM_Test<T> extends AbstractCollectionTest<T> {

    public CollectionSortedBagTest2(final String testName) {
        super(testName);
    }

    @Override
    public boolean isNullSupported() {
        return false;
    }

    @Override
    public Bag<T> makeObject() {
        return CollectionSortedBag.collectionSortedBag(new TreeBag<T>());
    }

    @Override
    public Collection<T> makeConfirmedCollection() {
        return new ArrayList<>();
    }

    @Override
    public Collection<T> makeConfirmedFullCollection() {
        final Collection<T> set = makeConfirmedCollection();
        set.addAll(Arrays.asList(getFullElements()));
        return set;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] getFullNonNullElements() {
        final Object[] elements = new Object[30];
        for (int i = 0; i < 30; i++) {
            elements[i] = Integer.valueOf(i + i + 1);
        }
        return (T[]) elements;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] getOtherNonNullElements() {
        final Object[] elements = new Object[30];
        for (int i = 0; i < 30; i++) {
            elements[i] = Integer.valueOf(i + i + 2);
        }
        return (T[]) elements;
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    public void testEmptyBagCompatibility() throws IOException, ClassNotFoundException {
        final Bag<T> bag = makeObject();
        if (bag instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final Bag<?> bag2 = (Bag<?>) readExternalFormFromDisk(getCanonicalEmptyCollectionName(bag));
            assertTrue("Bag is empty", bag2.size() == 0);
            assertEquals(bag, bag2);
        }
    }

    public void testFullBagCompatibility() throws IOException, ClassNotFoundException {
        final SortedBag<T> bag = (SortedBag<T>) makeFullCollection();
        if (bag instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final SortedBag<?> bag2 = (SortedBag<?>) readExternalFormFromDisk(getCanonicalFullCollectionName(bag));
            assertEquals("Bag is the right size", bag.size(), bag2.size());
            assertEquals(bag, bag2);
        }
    }

    // Test to ensure containsAll method works correctly with the updated code
    public void testContainsAll() {
        final SortedBag<T> bag = (SortedBag<T>) makeFullCollection();
        final Collection<T> elements = Arrays.asList(getFullNonNullElements());
        assertTrue("Bag should contain all elements", bag.containsAll(elements));
        elements.add((T) Integer.valueOf(-1)); // Add an element not in the bag
        assertFalse("Bag should not contain all elements", bag.containsAll(elements));
    }
}