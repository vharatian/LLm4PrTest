package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class TypeUtilsLLM_Test {

    @Test
    public void testGetTypeArgumentsWithFinalMap() {
        // Setup the types and type variables
        final ParameterizedType parameterizedType = TypeUtils.parameterize(Map.class, String.class, Integer.class);
        final TypeVariable<?>[] typeParams = Map.class.getTypeParameters();
        
        // Create a final map to hold type variable assignments
        final Map<TypeVariable<?>, Type> typeVarAssigns = new HashMap<>();
        
        // Call the method under test
        final Map<TypeVariable<?>, Type> result = TypeUtils.getTypeArguments(parameterizedType, Map.class, typeVarAssigns);
        
        // Verify the results
        assertEquals(2, result.size());
        assertTrue(result.containsKey(typeParams[0]));
        assertTrue(result.containsKey(typeParams[1]));
        assertEquals(String.class, result.get(typeParams[0]));
        assertEquals(Integer.class, result.get(typeParams[1]));
    }
}