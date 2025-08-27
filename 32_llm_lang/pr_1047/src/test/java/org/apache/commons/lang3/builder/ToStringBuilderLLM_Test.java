package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ToStringBuilderLLM_Test {

    @Test
    public void testSetDefaultStyleThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ToStringBuilder.setDefaultStyle(null));
    }
}