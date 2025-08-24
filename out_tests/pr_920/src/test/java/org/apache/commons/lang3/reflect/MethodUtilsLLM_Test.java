package org.apache.commons.lang3.reflect;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ClassUtils.Interfaces;
import org.apache.commons.lang3.reflect.testbed.Annotated;
import org.apache.commons.lang3.reflect.testbed.PublicChild;
import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    @Test
    @Annotated
    public void testGetMethodsWithAnnotation() throws NoSuchMethodException {
        assertArrayEquals(new Method[0], MethodUtils.getMethodsWithAnnotation(Object.class, Annotated.class));
        final Method[] methodsWithAnnotation = MethodUtils.getMethodsWithAnnotation(MethodUtilsTest2.class, Annotated.class);
        assertEquals(2, methodsWithAnnotation.length);
        assertThat(methodsWithAnnotation, hasItemInArray(MethodUtilsTest2.class.getMethod("testGetMethodsWithAnnotation")));
        assertThat(methodsWithAnnotation, hasItemInArray(MethodUtilsTest2.class.getMethod("testGetMethodsListWithAnnotation")));
    }

    @Test
    public void testGetMethodsWithAnnotationSearchSupersAndIgnoreAccess() {
        assertArrayEquals(new Method[0], MethodUtils.getMethodsWithAnnotation(Object.class, Annotated.class, true, true));
        final Method[] methodsWithAnnotation = MethodUtils.getMethodsWithAnnotation(PublicChild.class, Annotated.class, true, true);
        assertEquals(4, methodsWithAnnotation.length);
        assertEquals("PublicChild", methodsWithAnnotation[0].getDeclaringClass().getSimpleName());
        assertEquals("PublicChild", methodsWithAnnotation[1].getDeclaringClass().getSimpleName());
        assertTrue(methodsWithAnnotation[0].getName().endsWith("AnnotatedMethod"));
        assertTrue(methodsWithAnnotation[1].getName().endsWith("AnnotatedMethod"));
        assertEquals("Foo.doIt", methodsWithAnnotation[2].getDeclaringClass().getSimpleName() + '.' + methodsWithAnnotation[2].getName());
        assertEquals("Parent.parentProtectedAnnotatedMethod", methodsWithAnnotation[3].getDeclaringClass().getSimpleName() + '.' + methodsWithAnnotation[3].getName());
    }

    @Test
    public void testGetMethodsWithAnnotationNotSearchSupersButIgnoreAccess() {
        assertArrayEquals(new Method[0], MethodUtils.getMethodsWithAnnotation(Object.class, Annotated.class, false, true));
        final Method[] methodsWithAnnotation = MethodUtils.getMethodsWithAnnotation(PublicChild.class, Annotated.class, false, true);
        assertEquals(2, methodsWithAnnotation.length);
        assertEquals("PublicChild", methodsWithAnnotation[0].getDeclaringClass().getSimpleName());
        assertEquals("PublicChild", methodsWithAnnotation[1].getDeclaringClass().getSimpleName());
        assertTrue(methodsWithAnnotation[0].getName().endsWith("AnnotatedMethod"));
        assertTrue(methodsWithAnnotation[1].getName().endsWith("AnnotatedMethod"));
    }

    @Test
    public void testGetMethodsWithAnnotationSearchSupersButNotIgnoreAccess() {
        assertArrayEquals(new Method[0], MethodUtils.getMethodsWithAnnotation(Object.class, Annotated.class, true, false));
        final Method[] methodsWithAnnotation = MethodUtils.getMethodsWithAnnotation(PublicChild.class, Annotated.class, true, false);
        assertEquals(2, methodsWithAnnotation.length);
        assertEquals("PublicChild.publicAnnotatedMethod", methodsWithAnnotation[0].getDeclaringClass().getSimpleName() + '.' + methodsWithAnnotation[0].getName());
        assertEquals("Foo.doIt", methodsWithAnnotation[1].getDeclaringClass().getSimpleName() + '.' + methodsWithAnnotation[1].getName());
    }

    @Test
    public void testGetMethodsWithAnnotationNotSearchSupersAndNotIgnoreAccess() {
        assertArrayEquals(new Method[0], MethodUtils.getMethodsWithAnnotation(Object.class, Annotated.class, false, false));
        final Method[] methodsWithAnnotation = MethodUtils.getMethodsWithAnnotation(PublicChild.class, Annotated.class, false, false);
        assertEquals(1, methodsWithAnnotation.length);
        assertEquals("PublicChild.publicAnnotatedMethod", methodsWithAnnotation[0].getDeclaringClass().getSimpleName() + '.' + methodsWithAnnotation[0].getName());
    }

    @Test
    @Annotated
    public void testGetMethodsListWithAnnotation() throws NoSuchMethodException {
        assertEquals(0, MethodUtils.getMethodsListWithAnnotation(Object.class, Annotated.class).size());
        final List<Method> methodWithAnnotation = MethodUtils.getMethodsListWithAnnotation(MethodUtilsTest2.class, Annotated.class);
        assertEquals(2, methodWithAnnotation.size());
        assertThat(methodWithAnnotation, hasItems(MethodUtilsTest2.class.getMethod("testGetMethodsWithAnnotation"), MethodUtilsTest2.class.getMethod("testGetMethodsListWithAnnotation")));
    }

    @Test
    public void testGetMethodsListWithAnnotationIllegalArgumentException1() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsListWithAnnotation(FieldUtilsTest.class, null));
    }

    @Test
    public void testGetMethodsListWithAnnotationIllegalArgumentException2() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsListWithAnnotation(null, Annotated.class));
    }

    @Test
    public void testGetMethodsListWithAnnotationIllegalArgumentException3() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsListWithAnnotation(null, null));
    }
}