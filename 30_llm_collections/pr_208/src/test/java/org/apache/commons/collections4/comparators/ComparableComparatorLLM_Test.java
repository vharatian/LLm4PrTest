package org.apache.commons.collections4.comparators;

import org.junit.Test;
import static org.junit.Assert.*;

public class ComparableComparatorLLM_Test extends ComparableComparatorTest {

    public ComparableComparatorTest2(final String testName) {
        super(testName);
    }

    @Test
    public void testCompareWithNulls() {
        ComparableComparator<Integer> comparator = new ComparableComparator<>();
        try {
            comparator.compare(null, 1);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            comparator.compare(1, null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            comparator.compare(null, null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testCompareWithInconsistentComparable() {
        ComparableComparator<InconsistentComparable> comparator = new ComparableComparator<>();
        InconsistentComparable obj1 = new InconsistentComparable(1);
        InconsistentComparable obj2 = new InconsistentComparable(2);

        assertEquals("InconsistentComparable objects should be compared correctly", -1, comparator.compare(obj1, obj2));
        assertEquals("InconsistentComparable objects should be compared correctly", 1, comparator.compare(obj2, obj1));
        assertEquals("InconsistentComparable objects should be compared correctly", 0, comparator.compare(obj1, obj1));
    }

    private static class InconsistentComparable implements Comparable<InconsistentComparable> {
        private final int value;

        public InconsistentComparable(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(InconsistentComparable o) {
            return Integer.compare(this.value, o.value);
        }
    }
}