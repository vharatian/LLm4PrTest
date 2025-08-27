package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.collection.AbstractCollectionTest;

public class CollectionBagLLM_Test<T> extends AbstractCollectionTest<T> {

    public CollectionBagTest2(final String testName) {
        super(testName);
    }

    @Override
    public Bag<T> makeObject() {
        return CollectionBag.collectionBag(new HashBag<T>());
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
        final Bag<T> bag = (Bag<T>) makeFullCollection();
        if (bag instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final Bag<?> bag2 = (Bag<?>) readExternalFormFromDisk(getCanonicalFullCollectionName(bag));
            assertEquals("Bag is the right size", bag.size(), bag2.size());
            assertEquals(bag, bag2);
        }
    }

    // Test to ensure containsAll method handles whitespace correctly
    public void testContainsAllWhitespaceHandling() {
        Bag<T> bag = makeObject();
        Collection<T> coll = makeConfirmedCollection();
        coll.add((T) "test");
        coll.add((T) " ");
        coll.add((T) "anotherTest");

        bag.add((T) "test");
        bag.add((T) " ");
        bag.add((T) "anotherTest");

        assertTrue("Bag should contain all elements including whitespace", bag.containsAll(coll));
    }
}