package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testSplitPreserveAllTokens_StringStringInt() {
        // Test case for "ab de fg" with null separator and 0 limit
        String input = "ab de fg";
        String[] expected = new String[]{"ab", "de", "fg"};
        String[] actual = StringUtils.splitPreserveAllTokens(input, null, 0);
        assertArrayEquals(expected, actual);

        // Test case for "ab   de fg" with null separator and 0 limit
        input = "ab   de fg";
        expected = new String[]{"ab", "", "", "de", "fg"};
        actual = StringUtils.splitPreserveAllTokens(input, null, 0);
        assertArrayEquals(expected, actual);
    }
}