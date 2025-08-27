package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class ArrayUtilsLLM_Test {

    @Test
    public void testToPrimitiveBooleanArray() {
        Boolean[] booleanArray = {Boolean.TRUE, Boolean.FALSE, Boolean.TRUE};
        boolean[] expected = {true, false, true};
        assertArrayEquals(expected, ArrayUtils.toPrimitive(booleanArray));
    }

    @Test
    public void testToPrimitiveBooleanArrayWithNull() {
        Boolean[] booleanArray = {Boolean.TRUE, null, Boolean.FALSE};
        boolean[] expected = {true, false, false};
        assertArrayEquals(expected, ArrayUtils.toPrimitive(booleanArray, false));
    }

    @Test
    public void testToPrimitiveCharacterArray() {
        Character[] charArray = {'a', 'b', 'c'};
        char[] expected = {'a', 'b', 'c'};
        assertArrayEquals(expected, ArrayUtils.toPrimitive(charArray));
    }

    @Test
    public void testToPrimitiveCharacterArrayWithNull() {
        Character[] charArray = {'a', null, 'c'};
        char[] expected = {'a', '\0', 'c'};
        assertArrayEquals(expected, ArrayUtils.toPrimitive(charArray, '\0'));
    }

    @Test
    public void testToPrimitiveByteArray() {
        Byte[] byteArray = {1, 2, 3};
        byte[] expected = {1, 2, 3};
        assertArrayEquals(expected, ArrayUtils.toPrimitive(byteArray));
    }

    @Test
    public void testToPrimitiveByteArrayWithNull() {
        Byte[] byteArray = {1, null, 3};
        byte[] expected = {1, 0, 3};
        assertArrayEquals(expected, ArrayUtils.toPrimitive(byteArray, (byte) 0));
    }

    @Test
    public void testToPrimitiveWithNullArray() {
        assertNull(ArrayUtils.toPrimitive((Boolean[]) null));
        assertNull(ArrayUtils.toPrimitive((Character[]) null));
        assertNull(ArrayUtils.toPrimitive((Byte[]) null));
    }
}