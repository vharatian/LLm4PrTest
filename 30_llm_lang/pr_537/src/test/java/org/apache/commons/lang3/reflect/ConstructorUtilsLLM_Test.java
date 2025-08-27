package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;

public class ConstructorUtilsLLM_Test {

    @Test
    public void testInvokeConstructorWithPrimitiveArray() throws Exception {
        // Test with primitive array
        int[] intArray = {1, 2, 3};
        TestBean testBean = ConstructorUtils.invokeConstructor(TestBean.class, (Object) intArray);
        assertEquals("(Object)", testBean.toString());
    }

    @Test
    public void testInvokeExactConstructorWithPrimitiveArray() throws Exception {
        // Test with primitive array
        int[] intArray = {1, 2, 3};
        assertThrows(NoSuchMethodException.class, () -> {
            ConstructorUtils.invokeExactConstructor(TestBean.class, (Object) intArray);
        });
    }
}