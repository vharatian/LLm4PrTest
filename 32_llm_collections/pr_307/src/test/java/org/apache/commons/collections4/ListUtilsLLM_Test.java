package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ListUtilsLLM_Test {

    @Test
    public void testIsEqualListWithNullElements() {
        List<String> list1 = new ArrayList<>(Arrays.asList("a", null, "c"));
        List<String> list2 = new ArrayList<>(Arrays.asList("a", null, "c"));
        List<String> list3 = new ArrayList<>(Arrays.asList("a", "b", "c"));
        List<String> list4 = new ArrayList<>(Arrays.asList("a", null, "d"));

        // Test lists with null elements
        assertTrue(ListUtils.isEqualList(list1, list2));
        assertFalse(ListUtils.isEqualList(list1, list3));
        assertFalse(ListUtils.isEqualList(list1, list4));
    }

    @Test
    public void testIsEqualListWithDifferentSizes() {
        List<String> list1 = new ArrayList<>(Arrays.asList("a", "b", "c"));
        List<String> list2 = new ArrayList<>(Arrays.asList("a", "b"));

        // Test lists of different sizes
        assertFalse(ListUtils.isEqualList(list1, list2));
    }
}