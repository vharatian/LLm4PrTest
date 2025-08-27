package org.apache.commons.text.similarity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import java.util.Locale;
import org.junit.jupiter.api.Test;

public class FuzzyScoreLLM_Test {

    private static final FuzzyScore ENGLISH_SCORE = new FuzzyScore(Locale.ENGLISH);

    @Test
    public void testGetFuzzyScore_NullTerm() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ENGLISH_SCORE.fuzzyScore(null, "not null");
        });
    }

    @Test
    public void testGetFuzzyScore_NullQuery() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ENGLISH_SCORE.fuzzyScore("not null", null);
        });
    }

    @Test
    public void testGetFuzzyScore_NullTermAndQuery() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ENGLISH_SCORE.fuzzyScore(null, null);
        });
    }
}