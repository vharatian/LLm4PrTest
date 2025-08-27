package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class TypeUtilsLLM_Test {

    @Test
    public void testGetTypeArgumentsWithDefault() {
        // Create a ParameterizedType instance for testing
        ParameterizedType parameterizedType = TypeUtils.parameterize(Map.class, String.class, Integer.class);
        Class<?> toClass = Map.class;

        // Create a map with type variable assignments
        Map<TypeVariable<?>, Type> subtypeVarAssigns = new HashMap<>();
        TypeVariable<?>[] typeParams = Map.class.getTypeParameters();
        subtypeVarAssigns.put(typeParams[0], String.class);
        subtypeVarAssigns.put(typeParams[1], Integer.class);

        // Call the method with the updated implementation
        Map<TypeVariable<?>, Type> typeVarAssigns = TypeUtils.getTypeArguments(parameterizedType, toClass, subtypeVarAssigns);

        // Verify the results
        assertEquals(2, typeVarAssigns.size());
        assertEquals(String.class, typeVarAssigns.get(typeParams[0]));
        assertEquals(Integer.class, typeVarAssigns.get(typeParams[1]));
    }

    @Test
    public void testGetTypeArgumentsWithDefaultEmptySubtypeVarAssigns() {
        // Create a ParameterizedType instance for testing
        ParameterizedType parameterizedType = TypeUtils.parameterize(Map.class, String.class, Integer.class);
        Class<?> toClass = Map.class;

        // Call the method with an empty map for subtypeVarAssigns
        Map<TypeVariable<?>, Type> typeVarAssigns = TypeUtils.getTypeArguments(parameterizedType, toClass, Collections.emptyMap());

        // Verify the results
        TypeVariable<?>[] typeParams = Map.class.getTypeParameters();
        assertEquals(2, typeVarAssigns.size());
        assertEquals(String.class, typeVarAssigns.get(typeParams[0]));
        assertEquals(Integer.class, typeVarAssigns.get(typeParams[1]));
    }

    @Test
    public void testGetTypeArgumentsWithDefaultNullSubtypeVarAssigns() {
        // Create a ParameterizedType instance for testing
        ParameterizedType parameterizedType = TypeUtils.parameterize(Map.class, String.class, Integer.class);
        Class<?> toClass = Map.class;

        // Call the method with null for subtypeVarAssigns
        Map<TypeVariable<?>, Type> typeVarAssigns = TypeUtils.getTypeArguments(parameterizedType, toClass, null);

        // Verify the results
        TypeVariable<?>[] typeParams = Map.class.getTypeParameters();
        assertEquals(2, typeVarAssigns.size());
        assertEquals(String.class, typeVarAssigns.get(typeParams[0]));
        assertEquals(Integer.class, typeVarAssigns.get(typeParams[1]));
    }
}