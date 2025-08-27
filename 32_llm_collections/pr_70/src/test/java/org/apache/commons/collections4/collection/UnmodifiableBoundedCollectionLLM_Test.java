package org.apache.commons.collections4.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.list.FixedSizeList;
import junit.framework.TestCase;

public class UnmodifiableBoundedCollectionLLM_Test<E> extends TestCase {

    public UnmodifiableBoundedCollectionTest2(final String testName) {
        super(testName);
    }

    @Override
    public Collection<E> makeObject() {
        final BoundedCollection<E> coll = FixedSizeList.<E>fixedSizeList(new ArrayList<E>());
        return UnmodifiableBoundedCollection.unmodifiableBoundedCollection(coll);
    }

    @Override
    public BoundedCollection<E> makeFullCollection() {
        final E[] allElements = getFullElements();
        final BoundedCollection<E> coll = FixedSizeList.<E>fixedSizeList(new ArrayList<>(Arrays.asList(allElements)));
        return UnmodifiableBoundedCollection.unmodifiableBoundedCollection(coll);
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
    public boolean isAddSupported() {
        return false;
    }

    @Override
    public boolean isRemoveSupported() {
        return false;
    }

    @Override
    protected boolean skipSerializedCanonicalTests() {
        return true;
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    public void testUnmodifiable() {
        assertTrue(makeObject() instanceof Unmodifiable);
        assertTrue(makeFullCollection() instanceof Unmodifiable);
    }

    public void testDecorateFactory() {
        final BoundedCollection<E> coll = makeFullCollection();
        assertSame(coll, UnmodifiableBoundedCollection.unmodifiableBoundedCollection(coll));
        try {
            UnmodifiableBoundedCollection.unmodifiableBoundedCollection(null);
            fail();
        } catch (final NullPointerException ex) {}
    }

    /**
     * Test that removeIf throws UnsupportedOperationException
     */
    public void testRemoveIf() {
        final BoundedCollection<E> coll = makeFullCollection();
        final UnmodifiableBoundedCollection<E> unmodifiableColl = (UnmodifiableBoundedCollection<E>) UnmodifiableBoundedCollection.unmodifiableBoundedCollection(coll);
        try {
            unmodifiableColl.removeIf(new Predicate<E>() {
                @Override
                public boolean test(E e) {
                    return true;
                }
            });
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }
}