package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SystemUtilsLLM_Test {

    @Test
    public void testIS_JAVA_12() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_12);
        } else if (javaVersion.startsWith("12")) {
            assertTrue(SystemUtils.IS_JAVA_12);
        } else {
            assertFalse(SystemUtils.IS_JAVA_12);
        }
    }

    @Test
    public void testIS_JAVA_13() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_13);
        } else if (javaVersion.startsWith("13")) {
            assertTrue(SystemUtils.IS_JAVA_13);
        } else {
            assertFalse(SystemUtils.IS_JAVA_13);
        }
    }
}