package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import org.apache.commons.lang3.text.StrBuilder;
import org.junit.jupiter.api.Test;

public class ObjectUtilsLLM_Test {

    @Test
    public void testIdentityToStringAppendable() throws IOException {
        final Integer i = Integer.valueOf(121);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));
        final Appendable appendable = new StringBuilder();
        ObjectUtils.identityToString(appendable, i);
        assertEquals(expected, appendable.toString());
        assertThrows(NullPointerException.class, () -> ObjectUtils.identityToString((Appendable) null, "tmp"));
        assertThrows(
            NullPointerException.class,
            () -> ObjectUtils.identityToString((Appendable) (new StringBuilder()), null));
    }

    @Test
    public void testIdentityToStringStrBuilder() {
        final Integer i = Integer.valueOf(102);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));
        final StrBuilder builder = new StrBuilder();
        ObjectUtils.identityToString(builder, i);
        assertEquals(expected, builder.toString());
        assertThrows(NullPointerException.class, () -> ObjectUtils.identityToString((StrBuilder) null, "tmp"));
        assertThrows(NullPointerException.class, () -> ObjectUtils.identityToString(new StrBuilder(), null));
    }

    @Test
    public void testIdentityToStringStringBuffer() {
        final Integer i = Integer.valueOf(45);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));
        final StringBuffer buffer = new StringBuffer();
        ObjectUtils.identityToString(buffer, i);
        assertEquals(expected, buffer.toString());
        assertThrows(NullPointerException.class, () -> ObjectUtils.identityToString((StringBuffer) null, "tmp"));
        assertThrows(NullPointerException.class, () -> ObjectUtils.identityToString(new StringBuffer(), null));
    }

    @Test
    public void testIdentityToStringStringBuilder() {
        final Integer i = Integer.valueOf(90);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));
        final StringBuilder builder = new StringBuilder();
        ObjectUtils.identityToString(builder, i);
        assertEquals(expected, builder.toString());
    }

    @Test
    public void testIdentityToStringStringBuilderInUse() {
        final Integer i = Integer.valueOf(90);
        final String expected = "ABC = java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));
        final StringBuilder builder = new StringBuilder("ABC = ");
        ObjectUtils.identityToString(builder, i);
        assertEquals(expected, builder.toString());
    }

    @Test
    public void testIdentityToStringStringBuilderNullStringBuilder() {
        assertThrows(NullPointerException.class, () -> ObjectUtils.identityToString((StringBuilder) null, "tmp"));
    }

    @Test
    public void testIdentityToStringStringBuilderNullValue() {
        assertThrows(NullPointerException.class, () -> ObjectUtils.identityToString(new StringBuilder(), null));
    }
}