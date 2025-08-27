package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.collections4.comparators.ComparableComparator;
import junit.framework.TestCase;

@SuppressWarnings("boxing")
public class CollatingIteratorLLM_Test extends TestCase {

    private Comparator<Integer> comparator = null;
    private ArrayList<Integer> evens = null;
    private ArrayList<Integer> odds = null;

    public CollatingIteratorTest2(final String testName) {
        super(testName);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        comparator = new ComparableComparator<>();
        evens = new ArrayList<>();
        odds = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (0 == i % 2) {
                evens.add(i);
            } else {
                odds.add(i);
            }
        }
    }

    /**
     * Test for setIterator method to ensure IndexOutOfBoundsException is thrown
     * when index is out of bounds.
     */
    public void testSetIteratorIndexOutOfBounds() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());

        try {
            iter.setIterator(-1, evens.iterator());
            fail("Expected IndexOutOfBoundsException for negative index");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }

        try {
            iter.setIterator(2, evens.iterator());
            fail("Expected IndexOutOfBoundsException for index >= size()");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }
}