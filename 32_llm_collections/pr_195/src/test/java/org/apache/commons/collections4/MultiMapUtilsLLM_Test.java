package org.apache.commons.collections4;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class MultiMapUtilsLLM_Test {

    /**
     * Test to ensure that the MultiMapUtils class cannot be instantiated.
     */
    @Test
    public void testMultiMapUtilsCannotBeInstantiated() {
        try {
            MultiMapUtils.class.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalAccessException || e.getCause() instanceof IllegalAccessException);
        }
    }
}