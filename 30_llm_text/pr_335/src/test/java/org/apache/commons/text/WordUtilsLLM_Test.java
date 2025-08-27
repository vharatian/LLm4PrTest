package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

public class WordUtilsLLM_Test {

    @Test
    public void testAbbreviateWithMinIndexUpper() {
        // Test case to cover the change in the abbreviate method where Math.min(index, upper) is used
        assertThat(WordUtils.abbreviate("012 3456789", 0, 5, null)).isEqualTo("012");
        assertThat(WordUtils.abbreviate("01234 56789", 5, 10, null)).isEqualTo("01234");
        assertThat(WordUtils.abbreviate("01 23 45 67 89", 9, -1, null)).isEqualTo("01 23 45 67");
        assertThat(WordUtils.abbreviate("01 23 45 67 89", 9, 10, null)).isEqualTo("01 23 45 6");
        assertThat(WordUtils.abbreviate("0123456789", 15, 20, null)).isEqualTo("0123456789");
    }

    @Test
    public void testAbbreviateWithMinIndexUpperAndAppendedString() {
        // Test case to cover the change in the abbreviate method where Math.min(index, upper) is used with appended string
        assertThat(WordUtils.abbreviate("012 3456789", 0, 5, "-")).isEqualTo("012-");
        assertThat(WordUtils.abbreviate("01234 56789", 5, 10, "-")).isEqualTo("01234-");
        assertThat(WordUtils.abbreviate("01 23 45 67 89", 9, -1, "abc")).isEqualTo("01 23 45 67abc");
        assertThat(WordUtils.abbreviate("01 23 45 67 89", 9, 10, "")).isEqualTo("01 23 45 6");
    }

    @Test
    public void testAbbreviateWithMinIndexUpperEdgeCases() {
        // Test edge cases for the change in the abbreviate method where Math.min(index, upper) is used
        assertThat(WordUtils.abbreviate("0123456789", 0, 0, "")).isEqualTo("");
        assertThat(WordUtils.abbreviate("0123456789", 0, 1, "")).isEqualTo("0");
        assertThat(WordUtils.abbreviate("0123456789", 0, 10, "")).isEqualTo("0123456789");
        assertThat(WordUtils.abbreviate("0123456789", 0, 11, "")).isEqualTo("0123456789");
    }
}