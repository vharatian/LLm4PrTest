package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Arrays;
import org.apache.commons.collections4.multiset.HashMultiSet;
import org.junit.Before;
import org.junit.Test;

public class MultiSetUtilsLLM_Test {

    private String[] fullArray;
    private MultiSet<String> multiSet;

    @Before
    public void setUp() {
        fullArray = new String[]{
            "a", "a", "b", "c", "d", "d", "d"
        };
        multiSet = new HashMultiSet<>(Arrays.asList(fullArray));
    }

    @Test
    public void testPrivateConstructor() {
        try {
            MultiSetUtils.class.getDeclaredConstructor().setAccessible(true);
            MultiSetUtils.class.getDeclaredConstructor().newInstance();
            fail("Instantiation of MultiSetUtils should not be allowed");
        } catch (Exception e) {
            assertEquals("class org.apache.commons.collections4.MultiSetUtils cannot be instantiated", e.getCause().getMessage());
        }
    }
}