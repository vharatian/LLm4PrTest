package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Test;

@SuppressWarnings("deprecation")
public class ArrayUtilsLLM_Test {

    @Test
    public void testAddWithNonNullArray() {
        String[] array = {"a", "b", "c"};
        String[] result = ArrayUtils.add(array, 1, "x");
        assertArrayEquals(new String[]{"a", "x", "b", "c"}, result);
    }

    @Test
    public void testAddWithNullArray() {
        String[] result = ArrayUtils.add(null, 0, "x");
        assertArrayEquals(new String[]{"x"}, result);
    }

    @Test
    public void testAddWithNullElement() {
        String[] array = {"a", "b", "c"};
        String[] result = ArrayUtils.add(array, 1, null);
        assertArrayEquals(new String[]{"a", null, "b", "c"}, result);
    }

    @Test
    public void testAddWithIndexOutOfBounds() {
        String[] array = {"a", "b", "c"};
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add(array, 4, "x"));
    }

    @Test
    public void testAddWithNegativeIndex() {
        String[] array = {"a", "b", "c"};
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add(array, -1, "x"));
    }
}