package org.apache.commons.collections4;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayUtilsLLM_Test {

    @Test
    public void testContains() {
        String[] array = {"a", "b", "c"};
        assertTrue(ArrayUtils.contains(array, "a"));
        assertFalse(ArrayUtils.contains(array, "d"));
        assertFalse(ArrayUtils.contains(array, null));
    }

    @Test
    public void testIndexOf() {
        String[] array = {"a", "b", "c"};
        assertEquals(0, ArrayUtils.indexOf(array, "a"));
        assertEquals(1, ArrayUtils.indexOf(array, "b"));
        assertEquals(2, ArrayUtils.indexOf(array, "c"));
        assertEquals(-1, ArrayUtils.indexOf(array, "d"));
        assertEquals(-1, ArrayUtils.indexOf(array, null));
    }

    @Test
    public void testIndexOfWithStartIndex() {
        String[] array = {"a", "b", "c", "a"};
        assertEquals(3, ArrayUtils.indexOf(array, "a", 1));
        assertEquals(1, ArrayUtils.indexOf(array, "b", 0));
        assertEquals(-1, ArrayUtils.indexOf(array, "a", 4));
        assertEquals(-1, ArrayUtils.indexOf(array, "d", 0));
        assertEquals(-1, ArrayUtils.indexOf(array, null, 0));
    }

    // Test to ensure the private constructor is not accessible
    @Test
    public void testPrivateConstructor() {
        try {
            java.lang.reflect.Constructor<ArrayUtils> constructor = ArrayUtils.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
            fail("Expected IllegalAccessException");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalAccessException || e.getCause() instanceof IllegalAccessException);
        }
    }
}