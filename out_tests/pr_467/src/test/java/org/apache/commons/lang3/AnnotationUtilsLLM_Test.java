package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnnotationUtilsLLM_Test {

    private AnnotationUtilsTest.TestAnnotation testAnnotation1;
    private AnnotationUtilsTest.TestAnnotation testAnnotation2;
    private AnnotationUtilsTest.NestAnnotation nestAnnotation;

    @BeforeEach
    public void setup() throws Exception {
        testAnnotation1 = AnnotationUtilsTest.class.getDeclaredField("dummy1").getAnnotation(AnnotationUtilsTest.TestAnnotation.class);
        testAnnotation2 = AnnotationUtilsTest.class.getDeclaredField("dummy2").getAnnotation(AnnotationUtilsTest.TestAnnotation.class);
        nestAnnotation = AnnotationUtilsTest.class.getDeclaredField("dummy4").getAnnotation(AnnotationUtilsTest.NestAnnotation.class);
    }

    @Test
    public void testGetShortClassName() {
        // Test for the updated getShortClassName method
        Class<?> annotationClass = testAnnotation1.annotationType();
        String shortClassName = new AnnotationUtils().TO_STRING_STYLE.getShortClassName(annotationClass);
        assertEquals("@" + annotationClass.getName(), shortClassName);

        Class<?> nonAnnotationClass = String.class;
        shortClassName = new AnnotationUtils().TO_STRING_STYLE.getShortClassName(nonAnnotationClass);
        assertEquals("", shortClassName);
    }

    @Test
    public void testEqualsWithDifferentAnnotationTypes() {
        // Test for the updated equals method with different annotation types
        assertFalse(AnnotationUtils.equals(testAnnotation1, nestAnnotation));
        assertFalse(AnnotationUtils.equals(nestAnnotation, testAnnotation1));
    }

    @Test
    public void testEqualsWithSameAnnotationTypes() {
        // Test for the updated equals method with same annotation types
        assertTrue(AnnotationUtils.equals(testAnnotation1, testAnnotation2));
        assertTrue(AnnotationUtils.equals(testAnnotation2, testAnnotation1));
    }

    @Test
    public void testEqualsWithNullAnnotationType() {
        // Test for the updated equals method with null annotation type
        InvocationHandler handler = (proxy, method, args) -> {
            if ("annotationType".equals(method.getName())) {
                return null;
            }
            return method.invoke(testAnnotation1, args);
        };
        Annotation proxyAnnotation = (Annotation) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{AnnotationUtilsTest.TestAnnotation.class},
                handler
        );

        assertFalse(AnnotationUtils.equals(testAnnotation1, proxyAnnotation));
        assertFalse(AnnotationUtils.equals(proxyAnnotation, testAnnotation1));
    }
}