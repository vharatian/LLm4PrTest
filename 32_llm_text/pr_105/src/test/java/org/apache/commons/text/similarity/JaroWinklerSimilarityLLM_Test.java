package org.apache.commons.text.similarity;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;

public class JaroWinklerSimilarityLLM_Test {

    private static JaroWinklerSimilarity similarity;

    @BeforeAll
    public static void setUp() {
        similarity = new JaroWinklerSimilarity();
    }

    @Test
    public void testGetJaroWinklerSimilarity_UsingStringUtils() {
        // Test cases to ensure StringUtils.equals is used correctly
        assertEquals(1d, similarity.apply("test", "test"), 0.00001d);
        assertEquals(1d, similarity.apply("TEST", "TEST"), 0.00001d);
        assertEquals(0d, similarity.apply("test", "TEST"), 0.00001d);
        assertEquals(0d, similarity.apply("test", "tEst"), 0.00001d);
    }
}