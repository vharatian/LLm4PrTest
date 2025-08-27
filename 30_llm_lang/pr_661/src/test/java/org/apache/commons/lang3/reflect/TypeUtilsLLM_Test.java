package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.junit.jupiter.api.Test;

public class TypeUtilsLLM_Test {

    @Test
    public void testContainsTypeVariablesWithGenericArrayType() {
        // Create a GenericArrayType with a TypeVariable as its component type
        final TypeVariable<Class<TypeUtilsTest2>> typeVariable = TypeUtilsTest2.class.getTypeParameters()[0];
        final GenericArrayType genericArrayType = TypeUtils.genericArrayType(typeVariable);

        // Test that containsTypeVariables returns true for a GenericArrayType with a TypeVariable component
        assertTrue(TypeUtils.containsTypeVariables(genericArrayType));

        // Create a GenericArrayType with a ParameterizedType as its component type
        final ParameterizedType parameterizedType = TypeUtils.parameterize(Comparable.class, typeVariable);
        final GenericArrayType genericArrayTypeWithParameterizedType = TypeUtils.genericArrayType(parameterizedType);

        // Test that containsTypeVariables returns true for a GenericArrayType with a ParameterizedType component containing a TypeVariable
        assertTrue(TypeUtils.containsTypeVariables(genericArrayTypeWithParameterizedType));

        // Create a GenericArrayType with a Class as its component type
        final GenericArrayType genericArrayTypeWithClass = TypeUtils.genericArrayType(String.class);

        // Test that containsTypeVariables returns false for a GenericArrayType with a Class component
        assertFalse(TypeUtils.containsTypeVariables(genericArrayTypeWithClass));
    }
}