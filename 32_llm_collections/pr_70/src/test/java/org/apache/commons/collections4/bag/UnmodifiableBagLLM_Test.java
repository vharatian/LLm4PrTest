package org.apache.commons.collections4.bag;

import java.util.Arrays;
import java.util.function.Predicate;
import junit.framework.Test;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.collection.AbstractCollectionTest;

public class UnmodifiableBagLLM_Test<E> extends AbstractBagTest<E> {
    public UnmodifiableBagTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(UnmodifiableBagTest2.class);
    }

    @Override
    public Bag<E> makeObject() {
        return UnmodifiableBag.unmodifiableBag(new HashBag<E>());
    }

    @Override
    public Bag<E> makeFullCollection() {
        final Bag<E> bag = new HashBag<>();
        bag.addAll(Arrays.asList(getFullElements()));
        return UnmodifiableBag.unmodifiableBag(bag);
    }

    @Override
    public Bag<E> getCollection() {
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
        final Bag<E> queue = makeFullCollection();
        assertSame(queue, UnmodifiableBag.unmodifiableBag(queue));
        try {
            UnmodifiableBag.unmodifiableBag(null);
            fail();
        } catch (final NullPointerException ex) {}
    }

    /**
     * Test that removeIf throws UnsupportedOperationException
     */
    public void testRemoveIf() {
        final Bag<E> bag = makeFullCollection();
        try {
            bag.removeIf(new Predicate<E>() {
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
        return "4";
    }
}