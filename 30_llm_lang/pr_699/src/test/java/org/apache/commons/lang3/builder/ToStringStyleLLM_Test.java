package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ToStringStyleLLM_Test {

    private static class ToStringStyleImpl extends ToStringStyle {
        private static final long serialVersionUID = 1L;
    }

    @Test
    public void testDefaultUseShortClassName() {
        final ToStringStyle style = new ToStringStyleImpl();
        assertFalse(style.isUseShortClassName());
    }

    @Test
    public void testDefaultFieldSeparatorAtStart() {
        final ToStringStyle style = new ToStringStyleImpl();
        assertFalse(style.isFieldSeparatorAtStart());
    }

    @Test
    public void testDefaultFieldSeparatorAtEnd() {
        final ToStringStyle style = new ToStringStyleImpl();
        assertFalse(style.isFieldSeparatorAtEnd());
    }
}