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
    public void testGetJaroWinklerDistance_EmptyStrings() {
        assertEquals(0d, distance.apply("", ""), 0.00001d);
    }

    @Test
    public void testGetJaroWinklerDistance_IdenticalStrings() {
        assertEquals(0d, distance.apply("foo", "foo"), 0.00001d);
    }

    @Test
    public void testGetJaroWinklerDistance_DifferentStrings() {
        assertEquals(1 - 0.94166d, distance.apply("foo", "foo "), 0.00001d);
        assertEquals(1 - 0.90666d, distance.apply("foo", "foo "), 0.00001d);
        assertEquals(1 - 0.86666d, distance.apply("foo", " foo "), 0.00001d);
        assertEquals(1 - 0.51111d, distance.apply("foo", " foo"), 0.00001d);
        assertEquals(1 - 0.92499d, distance.apply("frog", "fog"), 0.00001d);
        assertEquals(1.0d, distance.apply("fly", "ant"), 0.00000000000000000001d);
        assertEquals(1 - 0.44166d, distance.apply("elephant", "hippo"), 0.00001d);
        assertEquals(1 - 0.90666d, distance.apply("ABC Corporation", "ABC Corp"), 0.00001d);
        assertEquals(1 - 0.95251d, distance.apply("D N H Enterprises Inc", "D & H Enterprises, Inc."), 0.00001d);
        assertEquals(1 - 0.942d, distance.apply("My Gym Children's Fitness Center", "My Gym. Childrens Fitness"), 0.00001d);
        assertEquals(1 - 0.898018d, distance.apply("PENNSYLVANIA", "PENNCISYLVNIA"), 0.00001d);
        assertEquals(1 - 0.971428d, distance.apply("/opt/software1", "/opt/software2"), 0.00001d);
        assertEquals(1 - 0.941666d, distance.apply("aaabcd", "aaacdb"), 0.00001d);
        assertEquals(1 - 0.911111d, distance.apply("John Horn", "John Hopkins"), 0.00001d);
    }

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

    @Test
    public void testMatchesMethod() {
        int[] result = JaroWinklerDistance.matches("dwayne", "duane");
        assertEquals(4, result[0]); // matches
        assertEquals(0, result[1]); // half transpositions
        assertEquals(1, result[2]); // prefix

        result = JaroWinklerDistance.matches("martha", "marhta");
        assertEquals(6, result[0]); // matches
        assertEquals(1, result[1]); // half transpositions
        assertEquals(3, result[2]); // prefix

        result = JaroWinklerDistance.matches("jones", "johnson");
        assertEquals(4, result[0]); // matches
        assertEquals(0, result[1]); // half transpositions
        assertEquals(1, result[2]); // prefix
    }
}