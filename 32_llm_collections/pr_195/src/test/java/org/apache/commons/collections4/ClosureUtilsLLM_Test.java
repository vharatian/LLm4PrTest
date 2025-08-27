package org.apache.commons.collections4;

import static org.junit.Assert.*;
import org.junit.Test;

public class ClosureUtilsLLM_Test {

    /**
     * Test to ensure ClosureUtils class cannot be instantiated.
     */
    @Test
    public void testClosureUtilsCannotBeInstantiated() {
        try {
            ClosureUtils.class.getDeclaredConstructor().newInstance();
            fail("Expected an InstantiationException to be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof InstantiationException);
        }
    }
}