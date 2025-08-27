package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.ObjectUtils;

public class ToStringStyleLLM_Test {

    private static class ToStringStyleImpl extends ToStringStyle {
        private static final long serialVersionUID = 1L;
    }

    @Test
    public void testAppendInternalWithArray() {
        final ToStringStyle style = new ToStringStyleImpl();
        final StringBuffer buffer = new StringBuffer();
        final String fieldName = "arrayField";
        final Object[] array = new Object[] { "one", "two", "three" };

        style.appendInternal(buffer, fieldName, array, true);
        assertEquals("[one,two,three]", buffer.toString());
    }

    @Test
    public void testAppendInternalWithNonArray() {
        final ToStringStyle style = new ToStringStyleImpl();
        final StringBuffer buffer = new StringBuffer();
        final String fieldName = "nonArrayField";
        final Object value = "testValue";

        style.appendInternal(buffer, fieldName, value, true);
        assertEquals("testValue", buffer.toString());
    }

    @Test
    public void testAppendInternalWithNull() {
        final ToStringStyle style = new ToStringStyleImpl();
        final StringBuffer buffer = new StringBuffer();
        final String fieldName = "nullField";

        style.appendInternal(buffer, fieldName, null, true);
        assertEquals("<null>", buffer.toString());
    }

    @Test
    public void testObjectUtilsIsArray() {
        final Object array = new int[] { 1, 2, 3 };
        final Object nonArray = "testString";

        assertEquals(true, ObjectUtils.isArray(array));
        assertEquals(false, ObjectUtils.isArray(nonArray));
    }
}