package org.apache.commons.collections4.comparators;

import org.junit.Test;
import static org.junit.Assert.*;

public class FixedOrderComparatorLLM_Test {

    @Test
    public void testDefaultValues() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>();
        assertEquals(0, comparator.counter);
        assertFalse(comparator.isLocked());
        assertEquals(FixedOrderComparator.UnknownObjectBehavior.EXCEPTION, comparator.getUnknownObjectBehavior());
    }

    @Test
    public void testUnknownObjectBehaviorAfterModification() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>();
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.AFTER);
        assertEquals(FixedOrderComparator.UnknownObjectBehavior.AFTER, comparator.getUnknownObjectBehavior());
    }

    @Test
    public void testUnknownObjectBehaviorBeforeModification() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>();
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        assertEquals(FixedOrderComparator.UnknownObjectBehavior.BEFORE, comparator.getUnknownObjectBehavior());
    }
}