package org.apache.commons.text.similarity;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JaroWinklerDistanceLLM_Test {

    private static JaroWinklerDistance distance;

    @BeforeAll
    public static void setUp() {
        distance = new JaroWinklerDistance();
    }

    @Test
    public void testMatchesMethod() {
        // Testing the matches method directly to ensure it handles the changes correctly
        int[] result1 = JaroWinklerDistance.matches("frog", "fog");
        assertEquals(3, result1[0]); // matches
        assertEquals(0, result1[1]); // half transpositions
        assertEquals(2, result1[2]); // prefix

        int[] result2 = JaroWinklerDistance.matches("elephant", "hippo");
        assertEquals(3, result2[0]); // matches
        assertEquals(1, result2[1]); // half transpositions
        assertEquals(0, result2[2]); // prefix

        int[] result3 = JaroWinklerDistance.matches("ABC Corporation", "ABC Corp");
        assertEquals(8, result3[0]); // matches
        assertEquals(0, result3[1]); // half transpositions
        assertEquals(4, result3[2]); // prefix

        int[] result4 = JaroWinklerDistance.matches("D N H Enterprises Inc", "D & H Enterprises, Inc.");
        assertEquals(17, result4[0]); // matches
        assertEquals(0, result4[1]); // half transpositions
        assertEquals(0, result4[2]); // prefix
    }
}