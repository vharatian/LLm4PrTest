package org.apache.commons.compress.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArchiveUtilsLLM_Test {

    @Test
    public void testIsEqualWithMinLenChange() {
        byte[] buffer1 = {1, 2, 3, 4, 5};
        byte[] buffer2 = {1, 2, 3, 4, 5};
        byte[] buffer3 = {1, 2, 3, 4, 0};

        // Test with equal buffers
        assertTrue(ArchiveUtils.isEqual(buffer1, 0, buffer1.length, buffer2, 0, buffer2.length, false));

        // Test with different buffers
        assertFalse(ArchiveUtils.isEqual(buffer1, 0, buffer1.length, buffer3, 0, buffer3.length, false));

        // Test with ignoreTrailingNulls = true
        assertTrue(ArchiveUtils.isEqual(buffer1, 0, buffer1.length, buffer3, 0, buffer3.length, true));
    }
}