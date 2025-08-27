package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListOrderedSetLLM_Test<E> {

    @Test
    public void testToArrayWithTypedArray() {
        // Setup
        ListOrderedSet<String> set = ListOrderedSet.listOrderedSet(new HashSet<>());
        set.add("one");
        set.add("two");
        set.add("three");

        // Create an array of the same type
        String[] array = new String[set.size()];

        // Execute
        String[] result = set.toArray(array);

        // Verify
        assertArrayEquals(new String[]{"one", "two", "three"}, result);
    }

    @Test
    public void testToArrayWithTypedArrayLarger() {
        // Setup
        ListOrderedSet<String> set = ListOrderedSet.listOrderedSet(new HashSet<>());
        set.add("one");
        set.add("two");
        set.add("three");

        // Create an array larger than the set size
        String[] array = new String[5];
        array[3] = "four";
        array[4] = "five";

        // Execute
        String[] result = set.toArray(array);

        // Verify
        assertArrayEquals(new String[]{"one", "two", "three", null, "five"}, result);
    }

    @Test
    public void testToArrayWithTypedArraySmaller() {
        // Setup
        ListOrderedSet<String> set = ListOrderedSet.listOrderedSet(new HashSet<>());
        set.add("one");
        set.add("two");
        set.add("three");

        // Create an array smaller than the set size
        String[] array = new String[2];

        // Execute
        String[] result = set.toArray(array);

        // Verify
        assertArrayEquals(new String[]{"one", "two", "three"}, result);
    }
}