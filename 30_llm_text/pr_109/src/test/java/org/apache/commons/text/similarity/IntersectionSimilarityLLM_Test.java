package org.apache.commons.text.similarity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class IntersectionSimilarityLLM_Test {

    @Test
    public void testNullConverter() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new IntersectionSimilarity<>(null);
        });
        assertEquals("Converter must not be null", thrown.getMessage());
    }

    @Test
    public void testNullInput() {
        Function<CharSequence, Collection<String>> converter = s -> Arrays.asList(s.toString().split(""));
        IntersectionSimilarity<String> similarity = new IntersectionSimilarity<>(converter);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            similarity.apply(null, "test");
        });
        assertEquals("Input cannot be null", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () -> {
            similarity.apply("test", null);
        });
        assertEquals("Input cannot be null", thrown.getMessage());
    }

    @Test
    public void testEmptyInput() {
        Function<CharSequence, Collection<String>> converter = s -> Arrays.asList(s.toString().split(""));
        IntersectionSimilarity<String> similarity = new IntersectionSimilarity<>(converter);

        IntersectionResult result = similarity.apply("", "test");
        assertEquals(0, result.getIntersection());
        assertEquals(0, result.getLeftSize());
        assertEquals(4, result.getRightSize());

        result = similarity.apply("test", "");
        assertEquals(0, result.getIntersection());
        assertEquals(4, result.getLeftSize());
        assertEquals(0, result.getRightSize());
    }

    @Test
    public void testSetIntersection() {
        Function<CharSequence, Collection<String>> converter = s -> new HashSet<>(Arrays.asList(s.toString().split("")));
        IntersectionSimilarity<String> similarity = new IntersectionSimilarity<>(converter);

        IntersectionResult result = similarity.apply("abc", "bcd");
        assertEquals(1, result.getIntersection());
        assertEquals(3, result.getLeftSize());
        assertEquals(3, result.getRightSize());
    }

    @Test
    public void testBagIntersection() {
        Function<CharSequence, Collection<String>> converter = s -> Arrays.asList(s.toString().split(""));
        IntersectionSimilarity<String> similarity = new IntersectionSimilarity<>(converter);

        IntersectionResult result = similarity.apply("aabbcc", "bbccdd");
        assertEquals(4, result.getIntersection());
        assertEquals(6, result.getLeftSize());
        assertEquals(6, result.getRightSize());
    }

    @Test
    public void testUniqueElementSize() {
        Function<CharSequence, Collection<String>> converter = s -> Arrays.asList(s.toString().split(""));
        IntersectionSimilarity<String> similarity = new IntersectionSimilarity<>(converter);

        IntersectionResult result = similarity.apply("abc", "def");
        assertEquals(0, result.getIntersection());
        assertEquals(3, result.getLeftSize());
        assertEquals(3, result.getRightSize());
    }
}