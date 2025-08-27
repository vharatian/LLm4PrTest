package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RandomStringGeneratorLLM_Test {

    /**
     * Test to ensure that selectFrom method handles null input gracefully.
     */
    @Test
    public void testSelectFromNullCharArray() {
        final RandomStringGenerator generator = new RandomStringGenerator.Builder().selectFrom((char[]) null).build();
        final String randomText = generator.generate(5);
        for (final char c : randomText.toCharArray()) {
            assertTrue(c >= Character.MIN_CODE_POINT && c <= Character.MAX_CODE_POINT);
        }
    }

    /**
     * Test to ensure that selectFrom method handles empty input array.
     */
    @Test
    public void testSelectFromEmptyCharArray() {
        final RandomStringGenerator generator = new RandomStringGenerator.Builder().selectFrom(new char[0]).build();
        final String randomText = generator.generate(5);
        for (final char c : randomText.toCharArray()) {
            assertTrue(c >= Character.MIN_CODE_POINT && c <= Character.MAX_CODE_POINT);
        }
    }
}