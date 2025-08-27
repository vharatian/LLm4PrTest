package org.apache.commons.collections4.multiset;

import java.util.Arrays;
import java.util.function.Predicate;
import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Unmodifiable;

public class UnmodifiableMultiSetLLM_Test<E> extends AbstractMultiSetTest<E> {

    public UnmodifiableMultiSetTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(UnmodifiableMultiSetTest2.class);
    }

    @Override
    public MultiSet<E> makeObject() {
        return UnmodifiableMultiSet.unmodifiableMultiSet(new HashMultiSet<E>());
    }

    @Override
    public MultiSet<E> makeFullCollection() {
        final MultiSet<E> multiset = new HashMultiSet<>();
        multiset.addAll(Arrays.asList(getFullElements()));
        return UnmodifiableMultiSet.unmodifiableMultiSet(multiset);
    }

    @Override
    public MultiSet<E> getCollection() {
        return super.getCollection();
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
    public boolean isNullSupported() {
        return false;
    }

    public void testUnmodifiable() {
        assertTrue(makeObject() instanceof Unmodifiable);
        assertTrue(makeFullCollection() instanceof Unmodifiable);
    }

    public void testDecorateFactory() {
        final MultiSet<E> multiset = makeFullCollection();
        assertSame(multiset, UnmodifiableMultiSet.unmodifiableMultiSet(multiset));
        try {
            UnmodifiableMultiSet.unmodifiableMultiSet(null);
            fail();
        } catch (final NullPointerException ex) {}
    }

    /**
     * Test that removeIf throws UnsupportedOperationException
     */
    public void testRemoveIf() {
        final MultiSet<E> multiset = makeFullCollection();
        try {
            multiset.removeIf(new Predicate<E>() {
                @Override
                public boolean test(E e) {
                    return true;
                }
            });
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException ex) {
            // expected
        }
    }

    @Override
    public String getCompatibilityVersion() {
        return "4.1";
    }
}