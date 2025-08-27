package org.apache.commons.collections4.comparators;

import org.junit.Test;
import static org.junit.Assert.*;

public class FixedOrderComparatorLLM_Test {

    @Test
    public void testHashCodeWithNonNullMap() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>("a", "b", "c");
        int expectedHashCode = 17;
        expectedHashCode = expectedHashCode * 37 + comparator.map.hashCode();
        expectedHashCode = expectedHashCode * 37 + comparator.getUnknownObjectBehavior().hashCode();
        expectedHashCode = expectedHashCode * 37 + 3; // counter is 3 after adding 3 elements
        expectedHashCode = expectedHashCode * 37 + 1; // isLocked is false, so 1
        assertEquals(expectedHashCode, comparator.hashCode());
    }
}