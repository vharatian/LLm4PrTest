package org.apache.commons.lang3.reflect;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.reflect.testbed.Annotated;
import org.apache.commons.lang3.reflect.testbed.PublicChild;
import org.apache.commons.lang3.reflect.testbed.StringParameterizedChild;
import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    @Test
    public void testGetMethodsWithAnnotationNullPointerException1() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsWithAnnotation(null, Annotated.class));
    }

    @Test
    public void testGetMethodsWithAnnotationNullPointerException2() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsWithAnnotation(MethodUtilsTest2.class, null));
    }

    @Test
    public void testGetMethodsWithAnnotationNullPointerException3() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsWithAnnotation(null, null));
    }

    @Test
    public void testGetMethodsListWithAnnotationNullPointerException1() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsListWithAnnotation(null, Annotated.class));
    }

    @Test
    public void testGetMethodsListWithAnnotationNullPointerException2() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsListWithAnnotation(MethodUtilsTest2.class, null));
    }

    @Test
    public void testGetMethodsListWithAnnotationNullPointerException3() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsListWithAnnotation(null, null));
    }

    @Test
    public void testGetAnnotationNullPointerException1() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getAnnotation(null, Annotated.class, true, true));
    }

    @Test
    public void testGetAnnotationNullPointerException2() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getAnnotation(null, null, true, true));
    }

    @Test
    public void testGetMethodsWithAnnotation() throws NoSuchMethodException {
        assertArrayEquals(new Method[0], MethodUtils.getMethodsWithAnnotation(Object.class, Annotated.class));
        final Method[] methodsWithAnnotation = MethodUtils.getMethodsWithAnnotation(MethodUtilsTest2.class, Annotated.class);
        assertEquals(2, methodsWithAnnotation.length);
        assertThat(methodsWithAnnotation, hasItemInArray(MethodUtilsTest2.class.getMethod("testGetMethodsWithAnnotation")));
        assertThat(methodsWithAnnotation, hasItemInArray(MethodUtilsTest2.class.getMethod("testGetMethodsListWithAnnotation")));
    }

    @Test
    public void testGetMethodsListWithAnnotation() throws NoSuchMethodException {
        assertEquals(0, MethodUtils.getMethodsListWithAnnotation(Object.class, Annotated.class).size());
        final List<Method> methodWithAnnotation = MethodUtils.getMethodsListWithAnnotation(MethodUtilsTest2.class, Annotated.class);
        assertEquals(2, methodWithAnnotation.size());
        assertThat(methodWithAnnotation, hasItems(
            MethodUtilsTest2.class.getMethod("testGetMethodsWithAnnotation"),
            MethodUtilsTest2.class.getMethod("testGetMethodsListWithAnnotation")
        ));
    }

    @Test
    public void testGetAnnotation() throws NoSuchMethodException {
        assertNull(MethodUtils.getAnnotation(PublicChild.class.getMethod("parentNotAnnotatedMethod"),
            Annotated.class, true, true));
        assertNotNull(MethodUtils.getAnnotation(PublicChild.class.getMethod("doIt"), Annotated.class,
            true, true));
        assertNotNull(MethodUtils.getAnnotation(PublicChild.class.getMethod("parentProtectedAnnotatedMethod"),
            Annotated.class, true, true));
        assertNotNull(MethodUtils.getAnnotation(PublicChild.class.getDeclaredMethod("privateAnnotatedMethod"),
            Annotated.class, true, true));
        assertNotNull(MethodUtils.getAnnotation(PublicChild.class.getMethod("publicAnnotatedMethod"),
            Annotated.class, true, true));
        assertNull(MethodUtils.getAnnotation(StringParameterizedChild.class.getMethod("parentNotAnnotatedMethod", String.class),
            Annotated.class, true, true));
        assertNotNull(MethodUtils.getAnnotation(StringParameterizedChild.class.getMethod("parentProtectedAnnotatedMethod", String.class),
            Annotated.class, true, true));
        assertNotNull(MethodUtils.getAnnotation(StringParameterizedChild.class.getDeclaredMethod("privateAnnotatedMethod", String.class),
            Annotated.class, true, true));
        assertNotNull(MethodUtils.getAnnotation(StringParameterizedChild.class.getMethod("publicAnnotatedMethod", String.class),
            Annotated.class, true, true));
    }
}