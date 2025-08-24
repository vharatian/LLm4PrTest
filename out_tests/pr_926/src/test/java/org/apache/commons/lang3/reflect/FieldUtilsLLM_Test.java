package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.reflect.testbed.StaticContainer;
import org.junit.jupiter.api.Test;

public class FieldUtilsLLM_Test {

    /**
     * Test to ensure the updated URL in the deprecated removeFinalModifier method is correct.
     */
    @Test
    public void testRemoveFinalModifierDeprecatedUrl() {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertTrue(field.isAccessible());
        assertTrue(Modifier.isFinal(field.getModifiers()));
        callRemoveFinalModifierCheckForException(field, true);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertTrue(Modifier.isFinal(field.getModifiers()));
            assertTrue(field.isAccessible());
        }
    }

    private void callRemoveFinalModifierCheckForException(final Field field, final Boolean forceAccess) {
        try {
            FieldUtils.removeFinalModifier(field, forceAccess);
        } catch (final UnsupportedOperationException exception) {
            if (SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_12)) {
                assertTrue(exception.getCause() instanceof NoSuchFieldException);
            } else {
                fail("No exception should be thrown for java prior to 12.0");
            }
        }
    }
}