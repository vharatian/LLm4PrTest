package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;

public class ToStringStyleLLM_Test {

    private static class ToStringStyleImpl extends ToStringStyle {
        private static final long serialVersionUID = 1L;
    }

    @Test
    public void testAppendDetailCollection() {
        final ToStringStyle style = new ToStringStyleImpl();
        final StringBuffer buffer = new StringBuffer();
        Collection<String> coll = Arrays.asList("one", "two", "three");
        style.appendDetail(buffer, "testField", coll);
        assertEquals("[one,two,three]", buffer.toString());
    }

    @Test
    public void testAppendDetailEmptyCollection() {
        final ToStringStyle style = new ToStringStyleImpl();
        final StringBuffer buffer = new StringBuffer();
        Collection<String> coll = Arrays.asList();
        style.appendDetail(buffer, "testField", coll);
        assertEquals("[]", buffer.toString());
    }

    @Test
    public void testAppendDetailNullCollection() {
        final ToStringStyle style = new ToStringStyleImpl();
        final StringBuffer buffer = new StringBuffer();
        Collection<String> coll = null;
        style.appendDetail(buffer, "testField", coll);
        assertEquals("null", buffer.toString());
    }
}