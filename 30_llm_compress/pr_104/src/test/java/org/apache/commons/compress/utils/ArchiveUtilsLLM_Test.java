package org.apache.commons.compress.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ArchiveUtilsLLM_Test {

    @Test
    public void testIsEqualWithTrailingNulls() {
        byte[] buffer1 = {1, 2, 3, 0, 0};
        byte[] buffer2 = {1, 2, 3};
        assertTrue(ArchiveUtils.isEqual(buffer1, buffer2, true));
    }

    @Test
    public void testIsEqualWithoutTrailingNulls() {
        byte[] buffer1 = {1, 2, 3, 0, 0};
        byte[] buffer2 = {1, 2, 3};
        assertFalse(ArchiveUtils.isEqual(buffer1, buffer2, false));
    }
}