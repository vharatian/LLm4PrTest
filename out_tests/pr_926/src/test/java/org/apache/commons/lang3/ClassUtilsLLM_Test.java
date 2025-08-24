package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClassUtilsLLM_Test {

    @Test
    @DisplayName("Test getPublicMethod with updated Java bug URL")
    public void testGetPublicMethodWithUpdatedJavaBugURL() throws Exception {
        final Set<?> set = Collections.unmodifiableSet(new HashSet<>());
        final Method isEmptyMethod = ClassUtils.getPublicMethod(set.getClass(), "isEmpty");
        assertTrue(Modifier.isPublic(isEmptyMethod.getDeclaringClass().getModifiers()));
        assertTrue((Boolean) isEmptyMethod.invoke(set));
    }

    @Test
    @DisplayName("Test isAssignable with updated Java Language Specification URL")
    public void testIsAssignableWithUpdatedJavaLanguageSpecificationURL() {
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.class));
        assertTrue(ClassUtils.isAssignable(Integer.class, Integer.TYPE));
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.TYPE));
        assertTrue(ClassUtils.isAssignable(Integer.class, Integer.class));
        assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.class));
        assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.TYPE));
        assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.TYPE));
        assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.class));
    }

    @Test
    @DisplayName("Test isAssignable with autoboxing and updated Java Language Specification URL")
    public void testIsAssignableWithAutoboxingAndUpdatedJavaLanguageSpecificationURL() {
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.class, true));
        assertTrue(ClassUtils.isAssignable(Integer.class, Integer.TYPE, true));
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.TYPE, true));
        assertTrue(ClassUtils.isAssignable(Integer.class, Integer.class, true));
        assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.class, true));
        assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.TYPE, true));
        assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.TYPE, true));
        assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.class, true));
    }

    @Test
    @DisplayName("Test isAssignable with no autoboxing and updated Java Language Specification URL")
    public void testIsAssignableWithNoAutoboxingAndUpdatedJavaLanguageSpecificationURL() {
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.TYPE, false));
        assertTrue(ClassUtils.isAssignable(Integer.class, Integer.class, false));
        assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.TYPE, false));
        assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.class, false));
    }

    @Test
    @DisplayName("Test isAssignable with class arrays and updated Java Language Specification URL")
    public void testIsAssignableClassArrayWithUpdatedJavaLanguageSpecificationURL() {
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = {Integer.TYPE, Boolean.TYPE};
        final Class<?>[] arrayWrappers = {Integer.class, Boolean.class};

        assertTrue(ClassUtils.isAssignable(array1s, array1s));
        assertTrue(ClassUtils.isAssignable(array1s, array1));
        assertTrue(ClassUtils.isAssignable(arrayPrimitives, arrayWrappers));
        assertTrue(ClassUtils.isAssignable(arrayWrappers, arrayPrimitives));
        assertTrue(ClassUtils.isAssignable(arrayPrimitives, array1));
        assertTrue(ClassUtils.isAssignable(arrayWrappers, array1));
    }

    @Test
    @DisplayName("Test isAssignable with class arrays and autoboxing, updated Java Language Specification URL")
    public void testIsAssignableClassArrayWithAutoboxingAndUpdatedJavaLanguageSpecificationURL() {
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = {Integer.TYPE, Boolean.TYPE};
        final Class<?>[] arrayWrappers = {Integer.class, Boolean.class};

        assertTrue(ClassUtils.isAssignable(array1s, array1s, true));
        assertTrue(ClassUtils.isAssignable(array1s, array1, true));
        assertTrue(ClassUtils.isAssignable(arrayPrimitives, arrayWrappers, true));
        assertTrue(ClassUtils.isAssignable(arrayWrappers, arrayPrimitives, true));
        assertTrue(ClassUtils.isAssignable(arrayPrimitives, array1, true));
        assertTrue(ClassUtils.isAssignable(arrayWrappers, array1, true));
    }

    @Test
    @DisplayName("Test isAssignable with class arrays and no autoboxing, updated Java Language Specification URL")
    public void testIsAssignableClassArrayWithNoAutoboxingAndUpdatedJavaLanguageSpecificationURL() {
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = {Integer.TYPE, Boolean.TYPE};
        final Class<?>[] arrayWrappers = {Integer.class, Boolean.class};

        assertTrue(ClassUtils.isAssignable(array1s, array1s, false));
        assertTrue(ClassUtils.isAssignable(array1s, array1, false));
        assertTrue(ClassUtils.isAssignable(arrayPrimitives, arrayWrappers, false));
        assertTrue(ClassUtils.isAssignable(arrayWrappers, arrayPrimitives, false));
        assertTrue(ClassUtils.isAssignable(arrayPrimitives, array1, false));
        assertTrue(ClassUtils.isAssignable(arrayWrappers, array1, false));
    }
}