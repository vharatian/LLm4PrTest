package org.apache.commons.collections4.bag;

import java.util.Arrays;
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

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    // New tests targeting the specific changes in the diff file

    /**
     * Test to ensure that the iterator returned is indeed unmodifiable.
     */
    public void testIteratorIsUnmodifiable() {
        Bag<E> bag = makeFullCollection();
        Iterator<E> iterator = bag.iterator();
        try {
            iterator.remove();
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }

    /**
     * Test to ensure that the uniqueSet returned is indeed unmodifiable.
     */
    public void testUniqueSetIsUnmodifiable() {
        Bag<E> bag = makeFullCollection();
        Set<E> uniqueSet = bag.uniqueSet();
        try {
            uniqueSet.add(getFullElements()[0]);
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }
}