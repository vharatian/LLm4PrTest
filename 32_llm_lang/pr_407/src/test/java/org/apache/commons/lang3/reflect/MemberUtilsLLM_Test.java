package org.apache.commons.lang3.reflect;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

public class MemberUtilsLLM_Test {

    @Test
    public void testGetTotalTransformationCostWithNullArrayElement() {
        Class<?>[] srcArgs = {Integer.TYPE, null};
        Method method = null;
        try {
            method = TestClass.class.getMethod("testMethod", int.class, int[].class);
        } catch (NoSuchMethodException e) {
            fail("Method not found");
        }
        float cost = MemberUtils.getTotalTransformationCost(srcArgs, MemberUtils.Executable.of(method));
        assertTrue(cost > 0, "Cost should be greater than 0 when srcArgs contains null");
    }

    @Test
    public void testGetPrimitivePromotionCostWithNullSrcClass() {
        float cost = MemberUtils.getPrimitivePromotionCost(null, Integer.TYPE);
        assertEquals(1.5f, cost, "Cost should be 1.5 when srcClass is null");
    }

    private static class TestClass {
        public void testMethod(int a, int... b) {}
    }
}