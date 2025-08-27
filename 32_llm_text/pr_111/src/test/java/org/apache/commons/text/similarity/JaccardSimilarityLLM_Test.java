package org.apache.commons.text.similarity;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JaccardSimilarityLLM_Test {
    private static JaccardSimilarity classBeingTested;

    @BeforeAll
    public static void setUp() {
        classBeingTested = new JaccardSimilarity();
    }

    @Test
    public void testGettingJaccardSimilarityRounded() {
        // Test cases to ensure the similarity score is not rounded
        assertEquals(0.2222222222222222, classBeingTested.apply("elephant", "hippo"));
        assertEquals(0.6363636363636364, classBeingTested.apply("ABC Corporation", "ABC Corp"));
        assertEquals(0.7647058823529411, classBeingTested.apply("D N H Enterprises Inc", "D & H Enterprises, Inc."));
        assertEquals(0.8888888888888888, classBeingTested.apply("My Gym Children's Fitness Center", "My Gym. Childrens Fitness"));
    }
}