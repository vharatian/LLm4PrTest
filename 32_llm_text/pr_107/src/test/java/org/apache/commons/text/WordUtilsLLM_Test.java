package org.apache.commons.text;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WordUtilsLLM_Test {

    @Test
    public void testWrapWithMatcherSizeZero() {
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", false, "(?=\\s)")).isEqualTo(expected);
        
        input = "Click here to jump to the commons website - http:";
        expected = "Click here to jump\nto the commons\nwebsite -\nhttp:";
        assertThat(WordUtils.wrap(input, 20, "\n", false, "(?=\\s)")).isEqualTo(expected);
    }

    @Test
    public void testWrapWithMatcherSizeZeroAndWrapLongWords() {
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, "(?=\\s)")).isEqualTo(expected);
        
        input = "Click here to jump to the commons website - http:";
        expected = "Click here to jump\nto the commons\nwebsite -\nhttp:";
        assertThat(WordUtils.wrap(input, 20, "\n", true, "(?=\\s)")).isEqualTo(expected);
    }

    @Test
    public void testWrapWithMatcherSizeZeroAndOffsetAdjustment() {
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", false, "(?=\\s)")).isEqualTo(expected);
        
        input = "Click here to jump to the commons website - http:";
        expected = "Click here to jump\nto the commons\nwebsite -\nhttp:";
        assertThat(WordUtils.wrap(input, 20, "\n", false, "(?=\\s)")).isEqualTo(expected);
    }

    @Test
    public void testWrapWithMatcherSizeZeroAndOffsetAdjustmentAndWrapLongWords() {
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, "(?=\\s)")).isEqualTo(expected);
        
        input = "Click here to jump to the commons website - http:";
        expected = "Click here to jump\nto the commons\nwebsite -\nhttp:";
        assertThat(WordUtils.wrap(input, 20, "\n", true, "(?=\\s)")).isEqualTo(expected);
    }
}