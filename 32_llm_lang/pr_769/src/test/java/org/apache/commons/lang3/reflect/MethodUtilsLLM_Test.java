package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    @Test
    public void testGetAllSuperclassesAndInterfaces() throws Exception {
        // Test case to cover the change in the getAllSuperclassesAndInterfaces method
        Class<?> cls = TestBean.class;
        MethodUtils methodUtils = new MethodUtils();
        Method getAllSuperclassesAndInterfacesMethod = MethodUtils.class.getDeclaredMethod("getAllSuperclassesAndInterfaces", Class.class);
        getAllSuperclassesAndInterfacesMethod.setAccessible(true);

        // Invoke the method and check the result
        List<Class<?>> result = (List<Class<?>>) getAllSuperclassesAndInterfacesMethod.invoke(methodUtils, cls);
        assertEquals(2, result.size());
        assertEquals(Object.class, result.get(0));
        assertEquals(TestBean.class, result.get(1));
    }

    @Test
    public void testGetAllSuperclassesAndInterfacesWithNull() throws Exception {
        // Test case to cover the change in the getAllSuperclassesAndInterfaces method when input is null
        MethodUtils methodUtils = new MethodUtils();
        Method getAllSuperclassesAndInterfacesMethod = MethodUtils.class.getDeclaredMethod("getAllSuperclassesAndInterfaces", Class.class);
        getAllSuperclassesAndInterfacesMethod.setAccessible(true);

        // Invoke the method with null and check the result
        List<Class<?>> result = (List<Class<?>>) getAllSuperclassesAndInterfacesMethod.invoke(methodUtils, (Class<?>) null);
        assertEquals(null, result);
    }
}