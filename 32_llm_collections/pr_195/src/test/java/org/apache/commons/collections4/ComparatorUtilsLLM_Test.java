package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.Comparator;
import org.junit.Test;

public class ComparatorUtilsLLM_Test {

    @Test
    public void testPrivateConstructor() {
        try {
            ComparatorUtils.class.getDeclaredConstructor().setAccessible(true);
            ComparatorUtils.class.getDeclaredConstructor().newInstance();
            fail("Expected an IllegalAccessException to be thrown");
        } catch (IllegalAccessException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Expected an IllegalAccessException, but got: " + e);
        }
    }
}