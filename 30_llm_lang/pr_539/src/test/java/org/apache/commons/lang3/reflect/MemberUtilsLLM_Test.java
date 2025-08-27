package org.apache.commons.lang3.reflect;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

class MemberUtilsLLM_Test {

    @Test
    void testGetTotalTransformationCostWithNoVarArgsPassed() throws Exception {
        // Setup
        Class<?>[] srcArgs = {Integer.class};
        Method method = TestClass.class.getMethod("methodWithVarArgs", Integer[].class);
        MemberUtils.Executable executable = MemberUtils.Executable.of(method);

        // Execute
        float cost = invokeGetTotalTransformationCost(srcArgs, executable);

        // Verify
        assertEquals(1.501f, cost, 0.001f);
    }

    @Test
    void testGetTotalTransformationCostWithExplicitArrayForVarargs() throws Exception {
        // Setup
        Class<?>[] srcArgs = {Integer[].class};
        Method method = TestClass.class.getMethod("methodWithVarArgs", Integer[].class);
        MemberUtils.Executable executable = MemberUtils.Executable.of(method);

        // Execute
        float cost = invokeGetTotalTransformationCost(srcArgs, executable);

        // Verify
        assertEquals(0.101f, cost, 0.001f);
    }

    private float invokeGetTotalTransformationCost(Class<?>[] srcArgs, MemberUtils.Executable executable) throws Exception {
        // Using reflection to access the private method
        java.lang.reflect.Method method = MemberUtils.class.getDeclaredMethod("getTotalTransformationCost", Class[].class, MemberUtils.Executable.class);
        method.setAccessible(true);
        return (float) method.invoke(null, srcArgs, executable);
    }

    static class TestClass {
        public void methodWithVarArgs(Integer... args) {}
    }
}