package org.apache.commons.lang3.reflect;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.testbed.Annotated;
import org.apache.commons.lang3.reflect.testbed.PublicChild;
import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    @Test
    public void testGetMethodsWithAnnotationNullClass() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsWithAnnotation(null, Annotated.class));
    }

    @Test
    public void testGetMethodsWithAnnotationNullAnnotation() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsWithAnnotation(PublicChild.class, null));
    }

    @Test
    public void testGetMethodsWithAnnotationNullClassAndAnnotation() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsWithAnnotation(null, null));
    }

    @Test
    public void testGetMethodsListWithAnnotationNullClass() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsListWithAnnotation(null, Annotated.class));
    }

    @Test
    public void testGetMethodsListWithAnnotationNullAnnotation() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsListWithAnnotation(PublicChild.class, null));
    }

    @Test
    public void testGetMethodsListWithAnnotationNullClassAndAnnotation() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsListWithAnnotation(null, null));
    }

    @Test
    public void testGetMethodsWithAnnotation() throws NoSuchMethodException {
        Method[] methodsWithAnnotation = MethodUtils.getMethodsWithAnnotation(MethodUtilsTest.class, Annotated.class);
        assertEquals(2, methodsWithAnnotation.length);
        assertThat(methodsWithAnnotation, hasItemInArray(MethodUtilsTest.class.getMethod("testGetMethodsWithAnnotation")));
        assertThat(methodsWithAnnotation, hasItemInArray(MethodUtilsTest.class.getMethod("testGetMethodsListWithAnnotation")));
    }

    @Test
    public void testGetMethodsListWithAnnotation() throws NoSuchMethodException {
        List<Method> methodWithAnnotation = MethodUtils.getMethodsListWithAnnotation(MethodUtilsTest.class, Annotated.class);
        assertEquals(2, methodWithAnnotation.size());
        assertThat(methodWithAnnotation, hasItems(
            MethodUtilsTest.class.getMethod("testGetMethodsWithAnnotation"),
            MethodUtilsTest.class.getMethod("testGetMethodsListWithAnnotation")
        ));
    }
}