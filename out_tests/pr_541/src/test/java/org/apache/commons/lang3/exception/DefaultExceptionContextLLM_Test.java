package org.apache.commons.lang3.exception;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultExceptionContextLLM_Test extends AbstractExceptionContextTest<DefaultExceptionContext> {

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        exceptionContext = new DefaultExceptionContext();
        super.setUp();
    }

    @Test
    public void testSetContextValue() {
        DefaultExceptionContext context = new DefaultExceptionContext();
        context.addContextValue("key1", "value1");
        context.addContextValue("key2", "value2");

        // Set a new value for an existing key
        context.setContextValue("key1", "newValue1");

        List<Pair<String, Object>> contextEntries = context.getContextEntries();
        assertEquals(2, contextEntries.size());
        assertTrue(contextEntries.contains(new ImmutablePair<>("key1", "newValue1")));
        assertTrue(contextEntries.contains(new ImmutablePair<>("key2", "value2")));

        // Set a value for a new key
        context.setContextValue("key3", "value3");
        contextEntries = context.getContextEntries();
        assertEquals(3, contextEntries.size());
        assertTrue(contextEntries.contains(new ImmutablePair<>("key3", "value3")));
    }
}