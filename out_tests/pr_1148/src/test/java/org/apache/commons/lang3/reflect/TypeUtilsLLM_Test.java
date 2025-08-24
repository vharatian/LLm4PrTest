package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class TypeUtilsLLM_Test {

    @Test
    public void testParameterizedTypeToStringWithOwner() {
        // Create a ParameterizedType with an owner type
        Type ownerType = TypeUtils.parameterize(TypeUtilsTest.class, String.class);
        ParameterizedType parameterizedType = TypeUtils.parameterizeWithOwner(ownerType, Map.Entry.class, String.class, Integer.class);

        // Verify the toString method handles the owner type correctly
        assertEquals("org.apache.commons.lang3.reflect.TypeUtilsTest<java.lang.String>.Entry<java.lang.String, java.lang.Integer>", parameterizedType.toString());
    }

    @Test
    public void testParameterizedTypeToStringWithoutOwner() {
        // Create a ParameterizedType without an owner type
        ParameterizedType parameterizedType = TypeUtils.parameterize(Map.Entry.class, String.class, Integer.class);

        // Verify the toString method handles the absence of an owner type correctly
        assertEquals("java.util.Map.Entry<java.lang.String, java.lang.Integer>", parameterizedType.toString());
    }

    @Test
    public void testParameterizedTypeToStringWithNonClassOwner() {
        // Create a ParameterizedType with a non-Class owner type
        Type ownerType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { String.class };
            }

            @Override
            public Type getRawType() {
                return Map.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public String toString() {
                return "CustomOwnerType";
            }
        };
        ParameterizedType parameterizedType = TypeUtils.parameterizeWithOwner(ownerType, Map.Entry.class, String.class, Integer.class);

        // Verify the toString method handles the non-Class owner type correctly
        assertEquals("CustomOwnerType.Entry<java.lang.String, java.lang.Integer>", parameterizedType.toString());
    }
}