package org.apache.commons.csv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DuplicateHeaderModeLLM_Test {

    @Test
    public void testAllowAll() {
        assertEquals(DuplicateHeaderMode.ALLOW_ALL, DuplicateHeaderMode.valueOf("ALLOW_ALL"));
    }

    @Test
    public void testAllowEmpty() {
        assertEquals(DuplicateHeaderMode.ALLOW_EMPTY, DuplicateHeaderMode.valueOf("ALLOW_EMPTY"));
    }

    @Test
    public void testDisallow() {
        assertEquals(DuplicateHeaderMode.DISALLOW, DuplicateHeaderMode.valueOf("DISALLOW"));
    }

    @Test
    public void testValues() {
        DuplicateHeaderMode[] expectedValues = {
            DuplicateHeaderMode.ALLOW_ALL,
            DuplicateHeaderMode.ALLOW_EMPTY,
            DuplicateHeaderMode.DISALLOW
        };
        assertArrayEquals(expectedValues, DuplicateHeaderMode.values());
    }
}