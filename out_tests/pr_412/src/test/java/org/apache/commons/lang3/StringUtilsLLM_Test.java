package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testJoin_ObjectArray_CharSeparator_StartIndex_EndIndex() {
        // Test for start index past the end of the array
        assertEquals("", StringUtils.join(new Object[]{"a", "b", "c"}, ',', 4, 5));
        assertEquals("", StringUtils.join(new Object[]{"a", "b", "c"}, ',', 3, 5));
    }

    @Test
    public void testJoin_longArray_CharSeparator_StartIndex_EndIndex() {
        // Test for start index past the end of the array
        assertEquals("", StringUtils.join(new long[]{1, 2, 3}, ',', 4, 5));
        assertEquals("", StringUtils.join(new long[]{1, 2, 3}, ',', 3, 5));
    }

    @Test
    public void testJoin_intArray_CharSeparator_StartIndex_EndIndex() {
        // Test for start index past the end of the array
        assertEquals("", StringUtils.join(new int[]{1, 2, 3}, ',', 4, 5));
        assertEquals("", StringUtils.join(new int[]{1, 2, 3}, ',', 3, 5));
    }

    @Test
    public void testJoin_byteArray_CharSeparator_StartIndex_EndIndex() {
        // Test for start index past the end of the array
        assertEquals("", StringUtils.join(new byte[]{1, 2, 3}, ',', 4, 5));
        assertEquals("", StringUtils.join(new byte[]{1, 2, 3}, ',', 3, 5));
    }

    @Test
    public void testJoin_shortArray_CharSeparator_StartIndex_EndIndex() {
        // Test for start index past the end of the array
        assertEquals("", StringUtils.join(new short[]{1, 2, 3}, ',', 4, 5));
        assertEquals("", StringUtils.join(new short[]{1, 2, 3}, ',', 3, 5));
    }

    @Test
    public void testJoin_charArray_CharSeparator_StartIndex_EndIndex() {
        // Test for start index past the end of the array
        assertEquals("", StringUtils.join(new char[]{'a', 'b', 'c'}, ',', 4, 5));
        assertEquals("", StringUtils.join(new char[]{'a', 'b', 'c'}, ',', 3, 5));
    }

    @Test
    public void testJoin_doubleArray_CharSeparator_StartIndex_EndIndex() {
        // Test for start index past the end of the array
        assertEquals("", StringUtils.join(new double[]{1.0, 2.0, 3.0}, ',', 4, 5));
        assertEquals("", StringUtils.join(new double[]{1.0, 2.0, 3.0}, ',', 3, 5));
    }

    @Test
    public void testJoin_List_CharSeparator_StartIndex_EndIndex() {
        // Test for start index past the end of the list
        assertEquals("", StringUtils.join(java.util.Arrays.asList("a", "b", "c"), ',', 4, 5));
        assertEquals("", StringUtils.join(java.util.Arrays.asList("a", "b", "c"), ',', 3, 5));
    }
}