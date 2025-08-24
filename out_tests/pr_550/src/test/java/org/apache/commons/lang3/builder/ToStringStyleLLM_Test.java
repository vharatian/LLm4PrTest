package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;

public class ToStringStyleLLM_Test {
    private static class ToStringStyleImpl extends ToStringStyle {
        private static final long serialVersionUID = 1L;
    }

    @Test
    public void testRemoveLastFieldSeparator() {
        final ToStringStyle style = new ToStringStyleImpl();
        StringBuffer buffer = new StringBuffer("test,");
        style.setFieldSeparator(",");
        style.removeLastFieldSeparator(buffer);
        assertEquals("test", buffer.toString());
    }

    @Test
    public void testRemoveLastFieldSeparatorNoMatch() {
        final ToStringStyle style = new ToStringStyleImpl();
        StringBuffer buffer = new StringBuffer("test;");
        style.setFieldSeparator(",");
        style.removeLastFieldSeparator(buffer);
        assertEquals("test;", buffer.toString());
    }

    @Test
    public void testRemoveLastFieldSeparatorEmptyBuffer() {
        final ToStringStyle style = new ToStringStyleImpl();
        StringBuffer buffer = new StringBuffer("");
        style.setFieldSeparator(",");
        style.removeLastFieldSeparator(buffer);
        assertEquals("", buffer.toString());
    }

    @Test
    public void testRemoveLastFieldSeparatorNullSeparator() {
        final ToStringStyle style = new ToStringStyleImpl();
        StringBuffer buffer = new StringBuffer("test,");
        style.setFieldSeparator(null);
        style.removeLastFieldSeparator(buffer);
        assertEquals("test,", buffer.toString());
    }

    @Test
    public void testRemoveLastFieldSeparatorEmptySeparator() {
        final ToStringStyle style = new ToStringStyleImpl();
        StringBuffer buffer = new StringBuffer("test,");
        style.setFieldSeparator("");
        style.removeLastFieldSeparator(buffer);
        assertEquals("test,", buffer.toString());
    }
}