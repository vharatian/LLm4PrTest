package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class WordUtilsLLM_Test {

    @Test
    public void testWrap_StringIntStringBooleanString_MatcherSize() {
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", false, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";
        assertThat(WordUtils.wrap(input, 20, "<br />", false, " ")).isEqualTo(expected);

        input = "Here is one line";
        expected = "Here\nis one\nline";
        assertThat(WordUtils.wrap(input, 6, "\n", false, " ")).isEqualTo(expected);

        expected = "Here\nis\none\nline";
        assertThat(WordUtils.wrap(input, 2, "\n", false, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);
    }

    @Test
    public void testWrap_StringIntStringBooleanString_MatcherSizeZero() {
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", false, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";
        assertThat(WordUtils.wrap(input, 20, "<br />", false, " ")).isEqualTo(expected);

        input = "Here is one line";
        expected = "Here\nis one\nline";
        assertThat(WordUtils.wrap(input, 6, "\n", false, " ")).isEqualTo(expected);

        expected = "Here\nis\none\nline";
        assertThat(WordUtils.wrap(input, 2, "\n", false, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertThat(WordUtils.wrap(input, 20, "\n", true, " ")).isEqualTo(expected);
    }
}