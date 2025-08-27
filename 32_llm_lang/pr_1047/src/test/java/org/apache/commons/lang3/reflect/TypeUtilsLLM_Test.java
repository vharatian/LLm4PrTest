package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class TypeUtilsLLM_Test {

    @Test
    public void testDetermineTypeArgumentsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TypeUtils.determineTypeArguments(null, (ParameterizedType) null));
        assertThrows(NullPointerException.class, () -> TypeUtils.determineTypeArguments(String.class, null));
        assertThrows(NullPointerException.class, () -> TypeUtils.determineTypeArguments(null, (ParameterizedType) String.class.getGenericInterfaces()[0]));
    }

    @Test
    public void testGetImplicitBoundsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TypeUtils.getImplicitBounds(null));
    }

    @Test
    public void testGetImplicitLowerBoundsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TypeUtils.getImplicitLowerBounds(null));
    }

    @Test
    public void testGetImplicitUpperBoundsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TypeUtils.getImplicitUpperBounds(null));
    }

    @Test
    public void testNormalizeUpperBoundsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TypeUtils.normalizeUpperBounds(null));
    }

    @Test
    public void testToLongStringNullPointerException() {
        assertThrows(NullPointerException.class, () -> TypeUtils.toLongString(null));
    }

    @Test
    public void testToStringNullPointerException() {
        assertThrows(NullPointerException.class, () -> TypeUtils.toString((Type) null));
    }

    @Test
    public void testTypesSatisfyVariablesNullPointerException() {
        assertThrows(NullPointerException.class, () -> TypeUtils.typesSatisfyVariables(null));
    }

    @Test
    public void testDetermineTypeArgumentsNoException() {
        assertDoesNotThrow(() -> {
            ParameterizedType parameterizedType = (ParameterizedType) String.class.getGenericInterfaces()[0];
            TypeUtils.determineTypeArguments(String.class, parameterizedType);
        });
    }

    @Test
    public void testGetImplicitBoundsNoException() {
        assertDoesNotThrow(() -> {
            TypeVariable<?> typeVariable = String.class.getTypeParameters()[0];
            TypeUtils.getImplicitBounds(typeVariable);
        });
    }

    @Test
    public void testGetImplicitLowerBoundsNoException() {
        assertDoesNotThrow(() -> {
            WildcardType wildcardType = (WildcardType) String.class.getGenericInterfaces()[0];
            TypeUtils.getImplicitLowerBounds(wildcardType);
        });
    }

    @Test
    public void testGetImplicitUpperBoundsNoException() {
        assertDoesNotThrow(() -> {
            WildcardType wildcardType = (WildcardType) String.class.getGenericInterfaces()[0];
            TypeUtils.getImplicitUpperBounds(wildcardType);
        });
    }

    @Test
    public void testNormalizeUpperBoundsNoException() {
        assertDoesNotThrow(() -> {
            Type[] bounds = new Type[] { String.class };
            TypeUtils.normalizeUpperBounds(bounds);
        });
    }

    @Test
    public void testToLongStringNoException() {
        assertDoesNotThrow(() -> {
            TypeVariable<?> typeVariable = String.class.getTypeParameters()[0];
            TypeUtils.toLongString(typeVariable);
        });
    }

    @Test
    public void testToStringNoException() {
        assertDoesNotThrow(() -> {
            Type type = String.class;
            TypeUtils.toString(type);
        });
    }

    @Test
    public void testTypesSatisfyVariablesNoException() {
        assertDoesNotThrow(() -> {
            Map<TypeVariable<?>, Type> typeVariableMap = new HashMap<>();
            typeVariableMap.put(String.class.getTypeParameters()[0], String.class);
            TypeUtils.typesSatisfyVariables(typeVariableMap);
        });
    }
}