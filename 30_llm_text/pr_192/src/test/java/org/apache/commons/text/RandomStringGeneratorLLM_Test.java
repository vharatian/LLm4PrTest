package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class RandomStringGeneratorLLM_Test {

    private static final CharacterPredicate A_FILTER = codePoint -> codePoint == 'a';
    private static final CharacterPredicate B_FILTER = codePoint -> codePoint == 'b';

    private static int codePointLength(final String s) {
        return s.codePointCount(0, s.length());
    }

    /**
     * Test to ensure the final keyword does not affect the functionality of codePoint generation.
     */
    @Test
    public void testGenerateWithFinalCodePoint() {
        final RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        final String str = generator.generate(100);
        assertThat(codePointLength(str)).isEqualTo(100);
    }
}