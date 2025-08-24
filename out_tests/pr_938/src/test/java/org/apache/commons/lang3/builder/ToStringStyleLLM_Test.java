package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class ToStringStyleLLM_Test extends AbstractLangTest {

    private static class ToStringStyleImpl extends ToStringStyle {
        private static final long serialVersionUID = 1L;
    }

    @Test
    public void testDefaultToStringStyleReadResolve() {
        ToStringStyle.DefaultToStringStyle style = new ToStringStyle.DefaultToStringStyle();
        assertEquals(ToStringStyle.DEFAULT_STYLE, style.readResolve());
    }

    @Test
    public void testNoFieldNameToStringStyleReadResolve() {
        ToStringStyle.NoFieldNameToStringStyle style = new ToStringStyle.NoFieldNameToStringStyle();
        assertEquals(ToStringStyle.NO_FIELD_NAMES_STYLE, style.readResolve());
    }

    @Test
    public void testMultiLineToStringStyleReadResolve() {
        ToStringStyle.MultiLineToStringStyle style = new ToStringStyle.MultiLineToStringStyle();
        assertEquals(ToStringStyle.MULTI_LINE_STYLE, style.readResolve());
    }

    @Test
    public void testNoClassNameToStringStyleReadResolve() {
        ToStringStyle.NoClassNameToStringStyle style = new ToStringStyle.NoClassNameToStringStyle();
        assertEquals(ToStringStyle.NO_CLASS_NAME_STYLE, style.readResolve());
    }

    @Test
    public void testJsonToStringStyleReadResolve() {
        ToStringStyle.JsonToStringStyle style = new ToStringStyle.JsonToStringStyle();
        assertEquals(ToStringStyle.JSON_STYLE, style.readResolve());
    }
}