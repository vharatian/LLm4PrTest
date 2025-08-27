package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class FluentIterableLLM_Test {

    @Test
    public void testPrivateConstructor() {
        try {
            FluentIterable.class.getDeclaredConstructor().newInstance();
            fail("Expected IllegalAccessException");
        } catch (IllegalAccessException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }
}