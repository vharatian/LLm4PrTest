package org.apache.commons.collections4;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class IteratorUtilsLLM_Test {

    /**
     * Test to ensure that the IteratorUtils class cannot be instantiated.
     */
    @Test
    public void testIteratorUtilsCannotBeInstantiated() {
        try {
            IteratorUtils.class.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalAccessException);
        }
    }
}