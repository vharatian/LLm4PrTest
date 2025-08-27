package org.apache.commons.text.similarity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class CosineSimilarityLLM_Test {

    @Test
    public void testCosineSimilarityWithPositiveValues() {
        final CosineSimilarity cosineSimilarity = new CosineSimilarity();
        final Map<CharSequence, Integer> leftVector = new HashMap<>();
        leftVector.put("a", 1);
        leftVector.put("b", 2);
        final Map<CharSequence, Integer> rightVector = new HashMap<>();
        rightVector.put("a", 2);
        rightVector.put("b", 3);
        assertThat(cosineSimilarity.cosineSimilarity(leftVector, rightVector)).isCloseTo(0.9926, within(0.0001));
    }

    @Test
    public void testCosineSimilarityWithNegativeValues() {
        final CosineSimilarity cosineSimilarity = new CosineSimilarity();
        final Map<CharSequence, Integer> leftVector = new HashMap<>();
        leftVector.put("a", -1);
        leftVector.put("b", -2);
        final Map<CharSequence, Integer> rightVector = new HashMap<>();
        rightVector.put("a", -2);
        rightVector.put("b", -3);
        assertThat(cosineSimilarity.cosineSimilarity(leftVector, rightVector)).isCloseTo(0.9926, within(0.0001));
    }

    @Test
    public void testCosineSimilarityWithMixedValues() {
        final CosineSimilarity cosineSimilarity = new CosineSimilarity();
        final Map<CharSequence, Integer> leftVector = new HashMap<>();
        leftVector.put("a", 1);
        leftVector.put("b", -2);
        final Map<CharSequence, Integer> rightVector = new HashMap<>();
        rightVector.put("a", -2);
        rightVector.put("b", 3);
        assertThat(cosineSimilarity.cosineSimilarity(leftVector, rightVector)).isCloseTo(-0.8, within(0.0001));
    }

    @Test
    public void testCosineSimilarityWithZeroValues() {
        final CosineSimilarity cosineSimilarity = new CosineSimilarity();
        final Map<CharSequence, Integer> leftVector = new HashMap<>();
        leftVector.put("a", 0);
        leftVector.put("b", 0);
        final Map<CharSequence, Integer> rightVector = new HashMap<>();
        rightVector.put("a", 0);
        rightVector.put("b", 0);
        assertThat(cosineSimilarity.cosineSimilarity(leftVector, rightVector)).isEqualTo(0.0, within(0.01));
    }
}