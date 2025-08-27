package org.apache.commons.lang3.reflect;

import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.reflect.testbed.StaticContainer;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class FieldUtilsLLM_Test {

    @Test
    public void testRemoveFinalModifierExceptionHandling() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertFalse(field.isAccessible());
        assertTrue(Modifier.isFinal(field.getModifiers()));

        try {
            FieldUtils.removeFinalModifier(field, true);
            if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
                assertFalse(Modifier.isFinal(field.getModifiers()));
                assertFalse(field.isAccessible());
            }
        } catch (UnsupportedOperationException e) {
            if (SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_12)) {
                assertTrue(e.getCause() instanceof NoSuchFieldException || e.getCause() instanceof IllegalAccessException);
            } else {
                fail("No exception should be thrown for java prior to 12.0");
            }
        }
    }

    @Test
    public void testRemoveFinalModifierWithIllegalAccessException() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertFalse(field.isAccessible());
        assertTrue(Modifier.isFinal(field.getModifiers()));

        assertThrows(UnsupportedOperationException.class, () -> {
            FieldUtils.removeFinalModifier(field, true);
        });
    }
}