package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    @Test
    public void testGetMatchingMethodThrowsIllegalStateException() {
        // Test case to ensure IllegalStateException is thrown when there is no unique result
        assertThrows(IllegalStateException.class, () -> 
            MethodUtils.getMatchingMethod(MethodUtilsTest.GetMatchingMethodClass.class, "testMethod2", (Class<?>) null)
        );
        assertThrows(IllegalStateException.class, () -> 
            MethodUtils.getMatchingMethod(MethodUtilsTest.GetMatchingMethodClass.class, "testMethod4", null, null)
        );
    }
}