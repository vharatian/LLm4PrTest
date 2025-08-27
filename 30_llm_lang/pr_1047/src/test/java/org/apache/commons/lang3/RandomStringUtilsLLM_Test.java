package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class RandomStringUtilsLLM_Test extends AbstractLangTest {

    /**
     * Test for IllegalArgumentException when count is less than 0 in various random methods.
     */
    @Test
    public void testRandomWithNegativeCount() {
        // Test for random(int count)
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1));

        // Test for random(int count, boolean letters, boolean numbers)
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1, true, true));

        // Test for random(int count, char... chars)
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1, 'a', 'b', 'c'));

        // Test for random(int count, int start, int end, boolean letters, boolean numbers)
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1, 0, 100, true, true));

        // Test for random(int count, int start, int end, boolean letters, boolean numbers, char... chars)
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1, 0, 100, true, true, 'a', 'b', 'c'));

        // Test for random(int count, String chars)
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1, "abc"));

        // Test for randomAlphabetic(int count)
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphabetic(-1));

        // Test for randomAlphanumeric(int count)
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphanumeric(-1));

        // Test for randomAscii(int count)
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAscii(-1));

        // Test for randomGraph(int count)
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomGraph(-1));

        // Test for randomNumeric(int count)
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomNumeric(-1));

        // Test for randomPrint(int count)
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomPrint(-1));
    }
}