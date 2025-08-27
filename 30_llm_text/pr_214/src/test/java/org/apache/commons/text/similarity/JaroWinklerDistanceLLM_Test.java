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

    /**
     * Test to verify the new implementation using JaroWinklerSimilarity.
     */
    @Test
    public void testGetJaroWinklerDistance_NewImplementation() {
        assertEquals(0.07501d, distance.apply("frog", "fog"), 0.00001d);
        assertEquals(1.0d, distance.apply("fly", "ant"), 0.00000000000000000001d);
        assertEquals(0.55834d, distance.apply("elephant", "hippo"), 0.00001d);
        assertEquals(0.09334d, distance.apply("ABC Corporation", "ABC Corp"), 0.00001d);
        assertEquals(0.04749d, distance.apply("D N H Enterprises Inc", "D & H Enterprises, Inc."), 0.00001d);
        assertEquals(0.058d, distance.apply("My Gym Children's Fitness Center", "My Gym. Childrens Fitness"), 0.00001d);
        assertEquals(0.101982d, distance.apply("PENNSYLVANIA", "PENNCISYLVNIA"), 0.00001d);
        assertEquals(0.028572d, distance.apply("/opt/software1", "/opt/software2"), 0.00001d);
        assertEquals(0.058334d, distance.apply("aaabcd", "aaacdb"), 0.00001d);
        assertEquals(0.088889d, distance.apply("John Horn", "John Hopkins"), 0.00001d);
    }

    /**
     * Test to verify IllegalArgumentException is thrown for null inputs.
     */
    @Test
    public void testGetJaroWinklerDistance_NullInputs() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            distance.apply(null, null);
        });
        assertThatIllegalArgumentException().isThrownBy(() -> {
            distance.apply(" ", null);
        });
        assertThatIllegalArgumentException().isThrownBy(() -> {
            distance.apply(null, "clear");
        });
    }
}