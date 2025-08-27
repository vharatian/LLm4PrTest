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
    public void testAddWithFinalClass() {
        // Test with non-null array and element
        String[] array = {"a", "b", "c"};
        String element = "d";
        String[] result = ArrayUtils.add(array, 1, element);
        assertArrayEquals(new String[]{"a", "d", "b", "c"}, result);

        // Test with null array and non-null element
        array = null;
        result = ArrayUtils.add(array, 0, element);
        assertArrayEquals(new String[]{"d"}, result);

        // Test with non-null array and null element
        array = new String[]{"a", "b", "c"};
        element = null;
        result = ArrayUtils.add(array, 1, element);
        assertArrayEquals(new String[]{"a", null, "b", "c"}, result);

        // Test with null array and null element
        array = null;
        result = ArrayUtils.add(array, 0, null);
        assertArrayEquals(new String[]{null}, result);

        // Test with invalid index
        array = new String[]{"a", "b", "c"};
        final String[] finalArray = array;
        final String finalElement = element;
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add(finalArray, -1, finalElement));
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add(finalArray, 4, finalElement));
    }
}