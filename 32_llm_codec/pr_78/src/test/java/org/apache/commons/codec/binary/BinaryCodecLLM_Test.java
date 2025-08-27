package org.apache.commons.codec.binary;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class BinaryCodecLLM_Test {

    @Test
    public void testIsEmptyWithNullArray() {
        byte[] array = null;
        assertTrue(BinaryCodec.isEmpty(array), "Expected true for null array");
    }

    @Test
    public void testIsEmptyWithEmptyArray() {
        byte[] array = new byte[0];
        assertTrue(BinaryCodec.isEmpty(array), "Expected true for empty array");
    }

    @Test
    public void testIsEmptyWithNonEmptyArray() {
        byte[] array = new byte[1];
        assertFalse(BinaryCodec.isEmpty(array), "Expected false for non-empty array");
    }
}