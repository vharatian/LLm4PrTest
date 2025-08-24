package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class UtilLLM_Test {

    /**
     * Test to ensure EMPTY_STRING_ARRAY is an empty array.
     */
    @Test
    public void testEmptyStringArray() {
        assertArrayEquals(new String[0], Util.EMPTY_STRING_ARRAY);
    }
}