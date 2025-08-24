package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ToStringBuilderLLM_Test {
    
    @Test
    public void testAppendAsObjectToStringNullPointerException() {
        // Test to ensure NullPointerException is thrown when srcObject is null
        ToStringBuilder builder = new ToStringBuilder(new Object());
        assertThrows(NullPointerException.class, () -> builder.appendAsObjectToString(null));
    }
}