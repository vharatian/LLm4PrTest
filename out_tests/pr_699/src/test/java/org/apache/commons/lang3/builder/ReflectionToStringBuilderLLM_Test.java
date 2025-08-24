package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ReflectionToStringBuilderLLM_Test {

    @Test
    public void testDefaultAppendStatics() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
        assertFalse(builder.isAppendStatics(), "Default value of appendStatics should be false");
    }

    @Test
    public void testDefaultAppendTransients() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
        assertFalse(builder.isAppendTransients(), "Default value of appendTransients should be false");
    }

    @Test
    public void testDefaultUpToClass() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
        assertTrue(builder.getUpToClass() == null, "Default value of upToClass should be null");
    }
}