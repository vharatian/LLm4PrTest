package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.commons.lang3.reflect.testbed.Annotated;
import org.apache.commons.lang3.reflect.testbed.PublicChild;
import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    @Test
    public void testGetAnnotationWithMatchingMethod() throws NoSuchMethodException {
        // Test case for the scenario where getMatchingMethod is used
        Method method = PublicChild.class.getDeclaredMethod("privateAnnotatedMethod");
        Annotation annotation = MethodUtils.getAnnotation(method, Annotated.class, true, true);
        assertNotNull(annotation);
    }

    @Test
    public void testGetAnnotationWithMatchingAccessibleMethod() throws NoSuchMethodException {
        // Test case for the scenario where getMatchingAccessibleMethod is used
        Method method = PublicChild.class.getMethod("doIt");
        Annotation annotation = MethodUtils.getAnnotation(method, Annotated.class, true, true);
        assertNotNull(annotation);
    }

    @Test
    public void testGetAnnotationWithNoMatchingMethod() throws NoSuchMethodException {
        // Test case for the scenario where no matching method is found
        Method method = PublicChild.class.getMethod("parentNotAnnotatedMethod");
        Annotation annotation = MethodUtils.getAnnotation(method, Annotated.class, true, true);
        assertNull(annotation);
    }

    @Test
    public void testGetAnnotationWithNoMatchingAccessibleMethod() throws NoSuchMethodException {
        // Test case for the scenario where no matching accessible method is found
        Method method = PublicChild.class.getMethod("parentProtectedAnnotatedMethod");
        Annotation annotation = MethodUtils.getAnnotation(method, Annotated.class, true, false);
        assertNull(annotation);
    }

    @Test
    public void testGetAnnotationIllegalArgumentException() {
        // Test case for IllegalArgumentException scenarios
        Method method = PublicChild.class.getDeclaredMethods()[0];
        assertThrows(IllegalArgumentException.class, () -> MethodUtils.getAnnotation(method, null, true, true));
        assertThrows(IllegalArgumentException.class, () -> MethodUtils.getAnnotation(null, Annotated.class, true, true));
        assertThrows(IllegalArgumentException.class, () -> MethodUtils.getAnnotation(null, null, true, true));
    }
}