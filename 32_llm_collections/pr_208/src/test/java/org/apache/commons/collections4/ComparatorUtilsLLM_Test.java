package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.Collection;
import java.util.Comparator;
import java.util.ArrayList;
import org.junit.Test;

public class ComparatorUtilsLLM_Test {

    @Test
    public void chainedComparatorWithCollection() {
        Collection<Comparator<Integer>> comparators = new ArrayList<>();
        comparators.add(ComparatorUtils.<Integer>naturalComparator());
        comparators.add(ComparatorUtils.naturalComparator());

        final Comparator<Integer> comp = ComparatorUtils.chainedComparator(comparators);
        assertTrue(comp.compare(1, 2) < 0);
        assertEquals(0, comp.compare(1, 1));
        assertTrue(comp.compare(2, 1) > 0);
    }
}