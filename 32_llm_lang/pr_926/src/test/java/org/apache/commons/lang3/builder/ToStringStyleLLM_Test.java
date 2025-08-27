package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class ToStringStyleLLM_Test extends AbstractLangTest {
    private static class ToStringStyleImpl extends ToStringStyle {
        private static final long serialVersionUID = 1L;
    }

    @Test
    public void testJsonStyleLink() {
        final ToStringStyle.JsonToStringStyle jsonStyle = new ToStringStyle.JsonToStringStyle();
        // Verify the JSON_STYLE constant is correctly initialized
        assertEquals(ToStringStyle.JSON_STYLE, jsonStyle);
    }
}