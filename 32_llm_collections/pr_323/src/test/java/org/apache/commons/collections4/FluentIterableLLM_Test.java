package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FluentIterableLLM_Test {

    @Test
    public void testCollateWithComparatorJavadoc() {
        // This test ensures that the Javadoc for the collate method with a comparator is correct.
        // Since the change was a typo fix in the Javadoc, we don't need to test functionality.
        // We can just ensure that the method is callable and works as expected.
        
        FluentIterable<Integer> iterableOdd = FluentIterable.of(1, 3, 5, 7, 9, 11);
        FluentIterable<Integer> iterableEven = FluentIterable.of(2, 4, 6, 8, 10, 12);
        
        FluentIterable<Integer> result = iterableOdd.collate(iterableEven, ComparatorUtils.<Integer>naturalComparator());
        assertEquals(12, result.size());
    }
}