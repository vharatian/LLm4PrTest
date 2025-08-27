package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testRepeat_StringStringInt() {
        // Test case based on the change in the diff file
        assertEquals("xx", StringUtils.repeat("", "x", 3));
    }
}