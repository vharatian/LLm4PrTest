package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testDeleteWhitespace_NoWhitespace() {
        // Test case where the input string has no whitespace
        assertEquals("", StringUtils.deleteWhitespace("abc"));
    }
}