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
    public void testGetJaroWinklerSimilarity_UpdatedValue() {
        // Updated test case based on the diff file
        assertEquals(0.91d, similarity.apply(wrap("ABC Corporation"), "ABC Corp"), 0.00001d);
    }

    private static CharSequence wrap(final String string) {
        return new CharSequence() {
            @Override
            public int length() {
                return string.length();
            }

            @Override
            public char charAt(final int index) {
                return string.charAt(index);
            }

            @Override
            public CharSequence subSequence(final int start, final int end) {
                return string.subSequence(start, end);
            }
        };
    }
}