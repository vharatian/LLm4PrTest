package org.apache.commons.text.similarity;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JaccardDistanceLLM_Test {
    private static JaccardDistance classBeingTested;

    @BeforeAll
    public static void setUp() {
        classBeingTested = new JaccardDistance();
    }

    @Test
    public void testGettingJaccardDistanceWithRounding() {
        // Test cases to ensure the rounding to two decimal places is removed
        assertEquals(1.0, classBeingTested.apply("", ""));
        assertEquals(1.0, classBeingTested.apply("left", ""));
        assertEquals(1.0, classBeingTested.apply("", "right"));
        assertEquals(0.25, classBeingTested.apply("frog", "fog"));
        assertEquals(1.0, classBeingTested.apply("fly", "ant"));
        assertEquals(0.7777777777777778, classBeingTested.apply("elephant", "hippo"));
        assertEquals(0.36363636363636365, classBeingTested.apply("ABC Corporation", "ABC Corp"));
        assertEquals(0.23529411764705888, classBeingTested.apply("D N H Enterprises Inc", "D & H Enterprises, Inc."));
        assertEquals(0.11111111111111116, classBeingTested.apply("My Gym Children's Fitness Center", "My Gym. Childrens Fitness"));
        assertEquals(0.09999999999999998, classBeingTested.apply("PENNSYLVANIA", "PENNCISYLVNIA"));
        assertEquals(0.875, classBeingTested.apply("left", "right"));
        assertEquals(0.875, classBeingTested.apply("leettteft", "ritttght"));
        assertEquals(0.0, classBeingTested.apply("the same string", "the same string"));
    }
}