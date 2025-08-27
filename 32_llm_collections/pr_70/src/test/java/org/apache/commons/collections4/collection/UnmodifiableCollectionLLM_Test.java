package org.apache.commons.collections4.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import org.apache.commons.collections4.Unmodifiable;
import junit.framework.TestCase;

public class UnmodifiableCollectionLLM_Test<E> extends TestCase {

    public UnmodifiableCollectionTest2(final String testName) {
        super(testName);
    }

    public Collection<E> makeObject() {
        return UnmodifiableCollection.unmodifiableCollection(new ArrayList<E>());
    }

    public Collection<E> makeFullCollection() {
        final List<E> list = new ArrayList<>();
        list.addAll(Arrays.asList(getFullElements()));
        return UnmodifiableCollection.unmodifiableCollection(list);
    }

    public Collection<E> makeConfirmedCollection() {
        return new ArrayList<>();
    }

    public Collection<E> makeConfirmedFullCollection() {
        final ArrayList<E> list = new ArrayList<>();
        list.addAll(Arrays.asList(getFullElements()));
        return list;
    }

    public boolean isAddSupported() {
        return false;
    }

    public boolean isRemoveSupported() {
        return false;
    }

    public void testUnmodifiable() {
        assertTrue(makeObject() instanceof Unmodifiable);
        assertTrue(makeFullCollection() instanceof Unmodifiable);
    }

    public void testDecorateFactory() {
        final Collection<E> coll = makeFullCollection();
        assertSame(coll, UnmodifiableCollection.unmodifiableCollection(coll));
        try {
            UnmodifiableCollection.unmodifiableCollection(null);
            fail();
        } catch (final NullPointerException ex) {}
    }

    public void testRemoveIf() {
        final Collection<E> coll = makeFullCollection();
        try {
            coll.removeIf(new Predicate<E>() {
                @Override
                public boolean test(E e) {
                    return false;
                }
            });
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }

    public String getCompatibilityVersion() {
        return "4";
    }

    // Helper method to get full elements for testing
    @SuppressWarnings("unchecked")
    private E[] getFullElements() {
        return (E[]) new Object[] {"element1", "element2", "element3"};
    }
}