package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class TypeUtilsLLM_Test {

    @Test
    public void testGetClosestParentType() {
        // Test case to ensure the change in getClosestParentType method works correctly
        final Class<?> cls = HashMap.class;
        final Class<?> superClass = Map.class;
        final Type closestParentType = TypeUtils.getClosestParentType(cls, superClass);

        assertTrue(closestParentType instanceof ParameterizedType);
        assertEquals(Map.class, ((ParameterizedType) closestParentType).getRawType());
    }
}