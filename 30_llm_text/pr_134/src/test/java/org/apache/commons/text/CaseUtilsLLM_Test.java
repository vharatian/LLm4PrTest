package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class CaseUtilsLLM_Test {

    @Test
    public void testToCamelCaseWithDelimiterAtStart() {
        // Test case where delimiter is at the start and capitalizeFirstLetter is false
        assertThat(CaseUtils.toCamelCase(" a b c", false, ' ')).isEqualTo("aBC");
    }

    @Test
    public void testToCamelCaseWithDelimiterAtStartAndCapitalizeFirstLetter() {
        // Test case where delimiter is at the start and capitalizeFirstLetter is true
        assertThat(CaseUtils.toCamelCase(" a b c", true, ' ')).isEqualTo("ABC");
    }

    @Test
    public void testToCamelCaseWithMultipleDelimiters() {
        // Test case with multiple delimiters and capitalizeFirstLetter is false
        assertThat(CaseUtils.toCamelCase("a-b_c", false, '-', '_')).isEqualTo("aBC");
    }

    @Test
    public void testToCamelCaseWithMultipleDelimitersAndCapitalizeFirstLetter() {
        // Test case with multiple delimiters and capitalizeFirstLetter is true
        assertThat(CaseUtils.toCamelCase("a-b_c", true, '-', '_')).isEqualTo("ABC");
    }

    @Test
    public void testToCamelCaseWithDelimiterAtEnd() {
        // Test case where delimiter is at the end and capitalizeFirstLetter is false
        assertThat(CaseUtils.toCamelCase("a b c ", false, ' ')).isEqualTo("aBC");
    }

    @Test
    public void testToCamelCaseWithDelimiterAtEndAndCapitalizeFirstLetter() {
        // Test case where delimiter is at the end and capitalizeFirstLetter is true
        assertThat(CaseUtils.toCamelCase("a b c ", true, ' ')).isEqualTo("ABC");
    }
}