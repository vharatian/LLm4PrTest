package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class NumberUtilsLLM_Test {

    @Test
    public void testMinLong_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.min((long[]) null));
    }

    @Test
    public void testMinInt_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.min((int[]) null));
    }

    @Test
    public void testMinShort_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.min((short[]) null));
    }

    @Test
    public void testMinByte_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.min((byte[]) null));
    }

    @Test
    public void testMinDouble_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.min((double[]) null));
    }

    @Test
    public void testMinFloat_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.min((float[]) null));
    }

    @Test
    public void testMaxLong_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.max((long[]) null));
    }

    @Test
    public void testMaxInt_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.max((int[]) null));
    }

    @Test
    public void testMaxShort_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.max((short[]) null));
    }

    @Test
    public void testMaxByte_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.max((byte[]) null));
    }

    @Test
    public void testMaxDouble_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.max((double[]) null));
    }

    @Test
    public void testMaxFloat_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.max((float[]) null));
    }
}