package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

public class ArrayUtilsLLM_Test {

    @Test
    public void testIsSortedComparatorNullPointerException() {
        Integer[] array = {1, 2, 3};
        assertThrows(NullPointerException.class, () -> ArrayUtils.isSorted(array, null));
    }

    @Test
    public void testIsSortedComparatorWithValidComparator() {
        Integer[] array = {3, 2, 1};
        Comparator<Integer> comparator = Comparator.reverseOrder();
        assertTrue(ArrayUtils.isSorted(array, comparator));
    }
}