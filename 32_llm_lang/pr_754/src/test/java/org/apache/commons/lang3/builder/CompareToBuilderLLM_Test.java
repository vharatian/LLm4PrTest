package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;

public class CompareToBuilderLLM_Test {

    @Test
    public void testAppendWithObjectUtilsIsArray() {
        final int[] array1 = {1, 2, 3};
        final int[] array2 = {1, 2, 3};
        final int[] array3 = {1, 2, 4};

        // Test equal arrays
        assertEquals(0, new CompareToBuilder().append(array1, array2).toComparison());

        // Test different arrays
        assertTrue(new CompareToBuilder().append(array1, array3).toComparison() < 0);
        assertTrue(new CompareToBuilder().append(array3, array1).toComparison() > 0);
    }

    @Test
    public void testAppendWithObjectUtilsIsArrayAndComparator() {
        final String[] array1 = {"a", "b", "c"};
        final String[] array2 = {"a", "b", "c"};
        final String[] array3 = {"a", "b", "d"};

        // Test equal arrays with comparator
        assertEquals(0, new CompareToBuilder().append(array1, array2, String.CASE_INSENSITIVE_ORDER).toComparison());

        // Test different arrays with comparator
        assertTrue(new CompareToBuilder().append(array1, array3, String.CASE_INSENSITIVE_ORDER).toComparison() < 0);
        assertTrue(new CompareToBuilder().append(array3, array1, String.CASE_INSENSITIVE_ORDER).toComparison() > 0);
    }

    @Test
    public void testAppendWithObjectUtilsIsArrayAndNullComparator() {
        final String[] array1 = {"a", "b", "c"};
        final String[] array2 = {"a", "b", "c"};
        final String[] array3 = {"a", "b", "d"};

        // Test equal arrays with null comparator
        assertEquals(0, new CompareToBuilder().append(array1, array2, null).toComparison());

        // Test different arrays with null comparator
        assertTrue(new CompareToBuilder().append(array1, array3, null).toComparison() < 0);
        assertTrue(new CompareToBuilder().append(array3, array1, null).toComparison() > 0);
    }
}