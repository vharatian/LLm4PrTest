package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

@Deprecated
public class StrBuilderLLM_Test {

    @Test
    public void testAppendFixedWidthPadLeft_Object() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft("test", 6, '*');
        assertEquals("**test", sb.toString());

        sb.clear();
        sb.appendFixedWidthPadLeft("longerString", 6, '*');
        assertEquals("String", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_Int() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft(123, 6, '*');
        assertEquals("***123", sb.toString());

        sb.clear();
        sb.appendFixedWidthPadLeft(123456789, 6, '*');
        assertEquals("456789", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_Object() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight("test", 6, '*');
        assertEquals("test**", sb.toString());

        sb.clear();
        sb.appendFixedWidthPadRight("longerString", 6, '*');
        assertEquals("longer", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_Int() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight(123, 6, '*');
        assertEquals("123***", sb.toString());

        sb.clear();
        sb.appendFixedWidthPadRight(123456789, 6, '*');
        assertEquals("123456", sb.toString());
    }
}