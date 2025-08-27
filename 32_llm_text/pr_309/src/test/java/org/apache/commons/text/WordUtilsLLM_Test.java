package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import org.junit.jupiter.api.Test;

public class WordUtilsLLM_Test {

    @Test
    public void testAbbreviateUpperCannotBeLowerThanLower() {
        // Test case to ensure that an IllegalArgumentException is thrown when upper is less than lower
        assertThatIllegalArgumentException().isThrownBy(() -> WordUtils.abbreviate("0123456789", 5, 2, ""));
    }

    @Test
    public void testAbbreviateUpperEqualToLower() {
        // Test case to ensure that the method works correctly when upper is equal to lower
        assertThat(WordUtils.abbreviate("0123456789", 5, 5, "")).isEqualTo("01234");
    }

    @Test
    public void testAbbreviateUpperGreaterThanLower() {
        // Test case to ensure that the method works correctly when upper is greater than lower
        assertThat(WordUtils.abbreviate("0123456789", 5, 7, "")).isEqualTo("01234");
    }
}