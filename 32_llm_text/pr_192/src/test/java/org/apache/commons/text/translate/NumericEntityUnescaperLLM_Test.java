package org.apache.commons.text.translate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class NumericEntityUnescaperLLM_Test {

    @Test
    public void testSemiColonRequiredOption() {
        NumericEntityUnescaper neu = new NumericEntityUnescaper(NumericEntityUnescaper.OPTION.semiColonRequired);
        String input = "Test &#1234 not test";
        String expected = "Test &#1234 not test";
        String result = neu.translate(input);
        assertThat(result).as("Failed to ignore unfinished entities with semiColonRequired option").isEqualTo(expected);
    }

    @Test
    public void testErrorIfNoSemiColonOption() {
        NumericEntityUnescaper neu = new NumericEntityUnescaper(NumericEntityUnescaper.OPTION.errorIfNoSemiColon);
        String input = "Test &#1234 not test";
        try {
            neu.translate(input);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException iae) {
            // Expected exception
        }
    }

    @Test
    public void testFinalEntityValue() {
        NumericEntityUnescaper neu = new NumericEntityUnescaper();
        String input = "&#x1F600;";
        String expected = "\uD83D\uDE00";
        String result = neu.translate(input);
        assertThat(result).as("Failed to unescape numeric entities with final entity value").isEqualTo(expected);
    }

    @Test
    public void testEnumSetInitialization() {
        NumericEntityUnescaper neu = new NumericEntityUnescaper();
        assertThat(neu.isSet(NumericEntityUnescaper.OPTION.semiColonRequired)).isTrue();
    }
}