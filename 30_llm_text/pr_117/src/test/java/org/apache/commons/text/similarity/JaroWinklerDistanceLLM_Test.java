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
    public void testGetJaroWinklerDistance_ABC_Corporation_ABC_Corp() {
        // Updated test case based on the diff file
        assertEquals(0.09d, distance.apply("ABC Corporation", "ABC Corp"), 0.00001d);
    }
}