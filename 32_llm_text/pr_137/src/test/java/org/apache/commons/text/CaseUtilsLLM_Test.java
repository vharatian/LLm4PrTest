package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class CaseUtilsLLM_Test {

    @Test
    public void testToCamelCaseWithTitleCaseCorrection() {
        // Test to ensure the title case correction in the Javadoc is reflected in the functionality
        assertThat(CaseUtils.toCamelCase("word", true)).isEqualTo("Word");
        assertThat(CaseUtils.toCamelCase("word", false)).isEqualTo("word");
        assertThat(CaseUtils.toCamelCase("word word", true, ' ')).isEqualTo("WordWord");
        assertThat(CaseUtils.toCamelCase("word word", false, ' ')).isEqualTo("wordWord");
    }
}