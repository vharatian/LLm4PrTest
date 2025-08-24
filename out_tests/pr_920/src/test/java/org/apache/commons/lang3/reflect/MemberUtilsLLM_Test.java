package org.apache.commons.lang3.reflect;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import static org.junit.jupiter.api.Assertions.*;

public class MemberUtilsLLM_Test {

    @Test
    public void testGetTotalTransformationCost() throws NoSuchMethodException {
        // Setup
        Class<?>[] srcArgs = {Integer.class, Double.class};
        Method method = TestClass.class.getMethod("testMethod", int.class, double.class);
        Constructor<?> constructor = TestClass.class.getConstructor(int.class, double.class);

        // Test method transformation cost
        float methodCost = MemberUtils.getTotalTransformationCost(srcArgs, MemberUtils.Executable.of(method));
        assertTrue(methodCost >= 0, "Transformation cost should be non-negative");

        // Test constructor transformation cost
        float constructorCost = MemberUtils.getTotalTransformationCost(srcArgs, MemberUtils.Executable.of(constructor));
        assertTrue(constructorCost >= 0, "Transformation cost should be non-negative");
    }

    private static class TestClass {
        public TestClass(int a, double b) {}
        public void testMethod(int a, double b) {}
    }
}