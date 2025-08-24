package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class CharSetUtilsLLM_Test {

    @Test
    public void testSqueeze_StringString() {
        // Test case to ensure the squeeze method works correctly with the updated code
        assertEquals("helo", CharSetUtils.squeeze("helloo", "l-p"));
    }

    @Test
    public void testSqueeze_StringStringarray() {
        // Test case to ensure the squeeze method works correctly with the updated code
        assertEquals("helo", CharSetUtils.squeeze("helloo", "el"));
    }
}