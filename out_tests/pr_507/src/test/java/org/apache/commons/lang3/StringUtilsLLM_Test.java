package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testAppendIfMissingIgnoreCase() {
        // Test cases based on the updated Javadoc example
        assertEquals("abcxyz", StringUtils.appendIfMissingIgnoreCase("abc", "xyz", "mno"));
    }
}