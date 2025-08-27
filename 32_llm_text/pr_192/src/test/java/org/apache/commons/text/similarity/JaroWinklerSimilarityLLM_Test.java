package org.apache.commons.text.similarity;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JaroWinklerSimilarityLLM_Test {

    private static JaroWinklerSimilarity similarity;

    @BeforeAll
    public static void setUp() {
        similarity = new JaroWinklerSimilarity();
    }

    @Test
    public void testMatchesMethodWithDifferentLengths() {
        // Test when first string is longer than the second
        int[] result1 = JaroWinklerSimilarity.matches("longerString", "short");
        assertEquals(3, result1[0]); // matches
        assertEquals(0, result1[1]); // half transpositions
        assertEquals(0, result1[2]); // prefix

        // Test when second string is longer than the first
        int[] result2 = JaroWinklerSimilarity.matches("short", "longerString");
        assertEquals(3, result2[0]); // matches
        assertEquals(0, result2[1]); // half transpositions
        assertEquals(0, result2[2]); // prefix
    }

    @Test
    public void testMatchesMethodWithEqualLengths() {
        // Test when both strings have equal length
        int[] result = JaroWinklerSimilarity.matches("same", "same");
        assertEquals(4, result[0]); // matches
        assertEquals(0, result[1]); // half transpositions
        assertEquals(4, result[2]); // prefix
    }

    @Test
    public void testMatchesMethodWithNoMatches() {
        // Test when there are no matches
        int[] result = JaroWinklerSimilarity.matches("abcd", "efgh");
        assertEquals(0, result[0]); // matches
        assertEquals(0, result[1]); // half transpositions
        assertEquals(0, result[2]); // prefix
    }
}