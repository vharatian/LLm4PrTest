package org.apache.commons.collections4.bag;

import junit.framework.Test;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.SortedBag;

public class TreeBagLLM_Test<T> extends AbstractSortedBagTest<T> {

    public TreeBagTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(TreeBagTest2.class);
    }

    @Override
    public SortedBag<T> makeObject() {
        return new TreeBag<>();
    }

    @SuppressWarnings("unchecked")
    public SortedBag<T> setupBag() {
        final SortedBag<T> bag = makeObject();
        bag.add((T) "C");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "D");
        return bag;
    }

    /**
     * Test to ensure that the TreeBag throws an IllegalArgumentException
     * when an object that is not Comparable is added and the comparator is null.
     */
    public void testAddNonComparableObjectWithNullComparator() {
        final Bag<Object> bag = new TreeBag<>();
        try {
            bag.add(new Object());
            fail("IllegalArgumentException expected");
        } catch (final IllegalArgumentException iae) {
            // Expected exception
        }
    }

    /**
     * Test to ensure that the TreeBag throws a NullPointerException
     * when a null object is added.
     */
    public void testAddNullObject() {
        final Bag<Object> bag = new TreeBag<>();
        try {
            bag.add(null);
            fail("NullPointerException expected");
        } catch (final NullPointerException npe) {
            // Expected exception
        }

        final Bag<String> bag2 = new TreeBag<>((o1, o2) -> o1.compareTo(o2));
        try {
            bag2.add("a");
            bag2.add(null);
            fail("NullPointerException expected");
        } catch (final NullPointerException npe) {
            // Expected exception
        }
    }

    /**
     * Test to ensure that the elements in the TreeBag are ordered correctly.
     */
    public void testOrdering() {
        final Bag<T> bag = setupBag();
        assertEquals("Should get elements in correct order", "A", bag.toArray()[0]);
        assertEquals("Should get elements in correct order", "B", bag.toArray()[1]);
        assertEquals("Should get elements in correct order", "C", bag.toArray()[2]);
        assertEquals("Should get first key", "A", ((SortedBag<T>) bag).first());
        assertEquals("Should get last key", "D", ((SortedBag<T>) bag).last());
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}