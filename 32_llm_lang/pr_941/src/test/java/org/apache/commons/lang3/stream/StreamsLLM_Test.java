package org.apache.commons.lang3.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class StreamsLLM_Test {

    @Test
    public void testEnumerationSpliteratorTryAdvance() {
        Hashtable<String, Integer> table = new Hashtable<>();
        table.put("One", 1);
        table.put("Two", 2);
        Enumeration<Integer> enumeration = table.elements();
        Streams.EnumerationSpliterator<Integer> spliterator = new Streams.EnumerationSpliterator<>(Long.MAX_VALUE, 0, enumeration);

        // Test tryAdvance with elements
        assertTrue(spliterator.tryAdvance(i -> assertTrue(i == 1 || i == 2)));
        assertTrue(spliterator.tryAdvance(i -> assertTrue(i == 1 || i == 2)));

        // Test tryAdvance without elements
        assertTrue(!spliterator.tryAdvance(i -> {}));
    }

    @Test
    public void testEnumerationSpliteratorForEachRemaining() {
        Hashtable<String, Integer> table = new Hashtable<>();
        table.put("One", 1);
        table.put("Two", 2);
        Enumeration<Integer> enumeration = table.elements();
        Streams.EnumerationSpliterator<Integer> spliterator = new Streams.EnumerationSpliterator<>(Long.MAX_VALUE, 0, enumeration);

        List<Integer> result = Streams.of(enumeration).collect(Collectors.toList());
        assertEquals(2, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }
}