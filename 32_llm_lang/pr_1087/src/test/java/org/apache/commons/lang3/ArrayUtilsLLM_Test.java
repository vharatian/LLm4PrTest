package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ArrayUtilsLLM_Test {

    @Test
    public void testAddWithClassParameter() {
        // Test for private static Object add(Object array, int index, Object element, Class<?> clazz)
        Object[] array = {"a", "b", "d"};
        Object[] result = (Object[]) ArrayUtils.add(array, 2, "c", String.class);
        assertArrayEquals(new Object[]{"a", "b", "c", "d"}, result);

        // Test for adding to an empty array
        array = new Object[]{};
        result = (Object[]) ArrayUtils.add(array, 0, "a", String.class);
        assertArrayEquals(new Object[]{"a"}, result);

        // Test for adding to a null array
        result = (Object[]) ArrayUtils.add(null, 0, "a", String.class);
        assertArrayEquals(new Object[]{"a"}, result);

        // Test for index out of bounds
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add(array, 2, "a", String.class));
    }

    @Test
    public void testDeprecatedAddWithClassParameter() {
        // Test for @Deprecated public static <T> T[] add(T[] array, int index, T element)
        String[] array = {"a", "b", "d"};
        String[] result = ArrayUtils.add(array, 2, "c");
        assertArrayEquals(new String[]{"a", "b", "c", "d"}, result);

        // Test for adding to an empty array
        array = new String[]{};
        result = ArrayUtils.add(array, 0, "a");
        assertArrayEquals(new String[]{"a"}, result);

        // Test for adding to a null array
        result = ArrayUtils.add(null, 0, "a");
        assertArrayEquals(new String[]{"a"}, result);

        // Test for index out of bounds
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add(array, 2, "a"));
    }
}