package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class WordUtilsLLM_Test {

    @Test
    public void testWrapWithMatcherSizeChange() {
        // Test case to verify the change in matcherSize calculation
        final String input = "This is a test string to check the wrap functionality.";
        final String expected = "This is a test\nstring to check\nthe wrap\nfunctionality.";
        assertThat(WordUtils.wrap(input, 15, "\n", false)).isEqualTo(expected);
    }

    @Test
    public void testWrapWithMatcherSizeChangeAtStart() {
        // Test case to verify the change in matcherSize calculation when the match is at the start
        final String input = " test string to check the wrap functionality.";
        final String expected = " test string\nto check the\nwrap\nfunctionality.";
        assertThat(WordUtils.wrap(input, 15, "\n", false)).isEqualTo(expected);
    }

    @Test
    public void testWrapWithMatcherSizeChangeWithLongWords() {
        // Test case to verify the change in matcherSize calculation with long words
        final String input = "Thisisaverylongwordthatneedstobewrappedproperly.";
        final String expected = "Thisisaverylong\nwordthatneedsto\nbewrappedproper\nly.";
        assertThat(WordUtils.wrap(input, 15, "\n", true)).isEqualTo(expected);
    }

    @Test
    public void testWrapWithMatcherSizeChangeWithSpecialCharacters() {
        // Test case to verify the change in matcherSize calculation with special characters
        final String input = "This is a test-string to check the wrap functionality.";
        final String expected = "This is a\ntest-string to\ncheck the wrap\nfunctionality.";
        assertThat(WordUtils.wrap(input, 15, "\n", false, "-")).isEqualTo(expected);
    }
}