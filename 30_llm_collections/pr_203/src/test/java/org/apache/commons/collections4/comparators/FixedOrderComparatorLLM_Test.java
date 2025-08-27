package org.apache.commons.collections4.comparators;

import org.junit.Test;
import static org.junit.Assert.*;

public class FixedOrderComparatorLLM_Test {

    @Test
    public void testUnknownObjectBehaviorEnum() {
        // Test that the enum values are correctly defined
        assertEquals("BEFORE", FixedOrderComparator.UnknownObjectBehavior.BEFORE.name());
        assertEquals("AFTER", FixedOrderComparator.UnknownObjectBehavior.AFTER.name());
        assertEquals("EXCEPTION", FixedOrderComparator.UnknownObjectBehavior.EXCEPTION.name());

        // Test that the enum values are in the correct order
        FixedOrderComparator.UnknownObjectBehavior[] values = FixedOrderComparator.UnknownObjectBehavior.values();
        assertEquals(FixedOrderComparator.UnknownObjectBehavior.BEFORE, values[0]);
        assertEquals(FixedOrderComparator.UnknownObjectBehavior.AFTER, values[1]);
        assertEquals(FixedOrderComparator.UnknownObjectBehavior.EXCEPTION, values[2]);
    }
}