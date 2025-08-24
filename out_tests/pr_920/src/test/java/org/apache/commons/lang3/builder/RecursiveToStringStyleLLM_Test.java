package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecursiveToStringStyleLLM_Test extends AbstractLangTest {

    private final Integer base = Integer.valueOf(5);
    private final String baseStr = base.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(base));

    @BeforeEach
    public void setUp() {
        ToStringBuilder.setDefaultStyle(new RecursiveToStringStyle());
    }

    @AfterEach
    public void tearDown() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.DEFAULT_STYLE);
    }

    @Test
    public void testAcceptMethod() {
        RecursiveToStringStyle style = new RecursiveToStringStyle();
        // Test that the accept method returns true for any class
        assertEquals(true, style.accept(String.class));
        assertEquals(true, style.accept(Integer.class));
        assertEquals(true, style.accept(Object.class));
    }
}